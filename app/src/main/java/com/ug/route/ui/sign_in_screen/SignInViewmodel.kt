package com.ug.route.ui.sign_in_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.gson.Gson
import com.ug.route.R
import com.ug.route.data.database.entities.UserEntity
import com.ug.route.networking.dto_models.FailResponse
import com.ug.route.networking.body_models.UserSignInBody
import com.ug.route.data.repositories.Repository
import com.ug.route.utils.Screen
import com.ug.route.utils.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    private val _screenState = MutableStateFlow(SignInState(
        message = "",
        launchedEffectKey = false,
        isLoading = false,
        isEmailError = false,
        passwordVisibility = false,
        isPasswordError = false
    ))

    val screenState = _screenState.asStateFlow()

    private val _user = MutableStateFlow(UserSignInBody("",""))
    val user = _user.asStateFlow()

    fun onChangePassword(newPassword : String){

        _user.update { it.copy(password = newPassword) }
    }
    fun onChangeEmail(newEmail : String){

        _user.update { it.copy(email = newEmail) }
    }

    fun signIn(navController: NavController) {

        if (checkInputError()) return

        viewModelScope.launch {

            _screenState.update { prevState ->
                prevState.copy(isLoading = true)
            }

            try {
                val response = withTimeout(5000L) {
                    repository.signIn(user.value)
                }

                val errorMessage = response.getErrorMessage()

                if (response.isSuccessful || ((repository.getUserByEmail(_user.value.email)?.password ?: "") == _user.value.password)) {

                    if (SharedPreferences.loggedEmail == null) repository.insertUser(
                        UserEntity(
                            id = 0,
                            name = "",
                            email = _user.value.email,
                            password = _user.value.password,
                            phone = "",
                            address = ""
                        )
                    )

                    SharedPreferences.loggedEmail = _user.value.email
                    
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }

                } else _screenState.update { it.copy(message = errorMessage ?: "An error occurred") }

            } catch (e: TimeoutCancellationException) {
                _screenState.update { prevState ->
                    prevState.copy(message = "Request timed out")
                }
            } catch (e: Exception) {
                _screenState.update { prevState ->
                    prevState.copy(message = "An error occurred")
                }
            } finally {
                _screenState.update { prevState ->
                    prevState.copy(isLoading = false,launchedEffectKey = !prevState.launchedEffectKey)
                }
            }
        }
    }

    fun onChangePasswordVisibility(passwordVisibility : Boolean) : Int{

        return if (passwordVisibility) R.drawable.visibility
        else R.drawable.visibility_off

    }

    fun updatePasswordVisibility(){
        _screenState.update { it.copy(passwordVisibility = !_screenState.value.passwordVisibility) }
    }

    private fun checkInputError(): Boolean {
        val user = user.value
        _screenState.update { prevState ->
            prevState.copy(
                isPasswordError = user.password.isEmpty(),
                isEmailError = user.email.isEmpty() || !("@" in user.email && "." in user.email)
            )
        }
        return _screenState.value.isEmailError || _screenState.value.isPasswordError
    }

    fun makeMessageEmpty(){
        _screenState.update { it.copy(message = "") }
    }

    private fun Response<*>.getErrorMessage(): String? {
        val errorResponse: FailResponse? = errorBody()?.charStream()?.use {
            Gson().fromJson(it, FailResponse::class.java)
        }
        return when (code()) {
            401 -> errorResponse?.message
            else -> errorResponse?.errors?.msg
        }
    }

    fun onInternetError(){
        _screenState.update { it.copy(
            message = "No Internet Connection",
            launchedEffectKey = !_screenState.value.launchedEffectKey) }
    }
}