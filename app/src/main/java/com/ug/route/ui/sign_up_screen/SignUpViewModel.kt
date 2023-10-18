package com.ug.route.ui.sign_up_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.gson.Gson
import com.ug.route.R
import com.ug.route.data.database.entities.UserEntity
import com.ug.route.networking.dto_models.FailResponse
import com.ug.route.data.repositories.Repository
import com.ug.route.networking.body_models.UserSignUpBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _user = MutableStateFlow(UserSignUpBody("","","","",""))
    val user = _user.asStateFlow()

    private val _screenState = MutableStateFlow(SignUpState(
        message = "",
        isPasswordError = false,
        isEmailError = false,
        isLoading = false,
        isPhoneError = false,
        isNameError = false,
        isRePasswordError = false,
        passwordVisibility = false,
        rePasswordVisibility = false,
        launchedEffectKey = false
    ))

    val screenState = _screenState.asStateFlow()

    fun onChangePassword(newPassword : String){

        _user.update { it.copy(password = newPassword) }
    }

    fun updatePasswordVisibility(){

        _screenState.update { it.copy(passwordVisibility = !_screenState.value.passwordVisibility) }
    }

    fun updateRePasswordVisibility(){

        _screenState.update { it.copy(rePasswordVisibility = !_screenState.value.rePasswordVisibility) }
    }

    fun onChangeRePassword(newPassword : String){

        _user.update { it.copy(rePassword = newPassword) }
    }

    fun onChangeEmail(newEmail : String){

        _user.update { it.copy(email = newEmail) }
    }

    fun onChangePhone(phone : String){

        _user.update { it.copy(phone = phone) }
    }

    fun onChangeName(name : String){

        _user.update { it.copy(name = name) }
    }

    fun onChangeVisibility(visibility : Boolean) : Int{

        return if (visibility) R.drawable.visibility
        else R.drawable.visibility_off

    }

    private fun checkInputError(): Boolean {
        val user = user.value
        _screenState.update { prevState ->
            prevState.copy(
                isPhoneError = user.phone.isEmpty(),
                isNameError = user.name.isEmpty() || user.name.length < 3,
                isPasswordError = user.password.isEmpty(),
                isRePasswordError = user.rePassword.isEmpty() || user.password != user.rePassword,
                isEmailError = user.email.isEmpty() || !("@" in user.email && "." in user.email)
            )
        }
        return _screenState.value.isPhoneError ||
                _screenState.value.isNameError ||
                _screenState.value.isPasswordError ||
                _screenState.value.isEmailError ||
                _screenState.value.isRePasswordError
    }



    fun signUp(navController: NavController) {

        if (checkInputError()) return

        viewModelScope.launch {
            _screenState.update { prevState ->
                prevState.copy(isLoading = true)
            }

            try {
                val response = withTimeout(5000L) {
                    repository.signUp(user.value)
                }

                val errorMessage = response.getErrorMessage()

                _screenState.update { prevState ->
                    if (response.isSuccessful) {
                        repository.insertUser(
                            UserEntity(
                                id = 0,
                                name = _user.value.name,
                                email = _user.value.email,
                                password = _user.value.password,
                                phone = _user.value.phone,
                                address = "")
                        )
                        prevState.copy(message = "Account Created Successfully")
                    } else {
                        prevState.copy(message = errorMessage ?: "An error occurred")
                    }.copy(launchedEffectKey = !prevState.launchedEffectKey)
                }
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
                    prevState.copy(isLoading = false)
                }
            }

            delay(1000)

            navController.popBackStack()
        }
    }

    private fun Response<*>.getErrorMessage(): String? {
        val errorResponse: FailResponse? = errorBody()?.charStream()?.use {
            Gson().fromJson(it, FailResponse::class.java)
        }
        return when (code()) {
            409 -> errorResponse?.message
            else -> errorResponse?.errors?.msg
        }
    }

    fun onInternetError(){
        _screenState.update { it.copy(
            message = "No Internet Connection",
            launchedEffectKey = !_screenState.value.launchedEffectKey) }
    }
}