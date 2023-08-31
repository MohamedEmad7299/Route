package com.ug.route.ui.reset_password_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.gson.Gson
import com.ug.route.R
import com.ug.route.data.models.ForgetPasswordResponse
import com.ug.route.data.repositories.Repository
import com.ug.route.networking.dto_models.ResetPasswordDTO
import com.ug.route.utils.Screen
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
class ResetPasswordViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _resetPasswordDto = MutableStateFlow(ResetPasswordDTO(
        email = checkNotNull(savedStateHandle["email"]),
        ""
    ))
    val resetPasswordDto = _resetPasswordDto.asStateFlow()

    private val _rePassword = MutableStateFlow("")
    val rePassword = _rePassword.asStateFlow()

    private val _screenState = MutableStateFlow(
        ResetPasswordState(
        message = "",
        isPasswordError = false,
        isLoading = false,
        isRePasswordError = false,
        passwordVisibility = false,
        rePasswordVisibility = false,
        launchedEffectKey = false))

    val screenState = _screenState.asStateFlow()

    fun updatePasswordVisibility(){

        _screenState.update { it.copy(passwordVisibility = !_screenState.value.passwordVisibility) }
    }

    fun updateRePasswordVisibility(){

        _screenState.update { it.copy(rePasswordVisibility = !_screenState.value.rePasswordVisibility) }
    }

    fun onChangeRePassword(newPassword : String){

        _rePassword.update { newPassword }
    }

    fun onChangePassword(newPassword : String){

        _resetPasswordDto.update { it.copy(newPassword = newPassword) }
    }

    fun onChangeVisibility(visibility : Boolean) : Int{

        return if (visibility) R.drawable.visibility
        else R.drawable.visibility_off

    }
    private fun checkInputError(): Boolean {

        val resetPasswordState = _resetPasswordDto.value
        _screenState.update { prevState ->
            prevState.copy(
                isPasswordError = resetPasswordState.newPassword.isEmpty() || resetPasswordState.newPassword.length < 6,
                isRePasswordError = _rePassword.value.isEmpty() || _rePassword.value != resetPasswordState.newPassword
            )
        }

        return  _screenState.value.isPasswordError ||
                _screenState.value.isRePasswordError
    }
    fun resetPassword(navController : NavController) {

        if (checkInputError()) return

        viewModelScope.launch {
            _screenState.update { prevState ->
                prevState.copy(isLoading = true)
            }

            try {

                val response = withTimeout(5000L) {
                    repository.resetPassword(_resetPasswordDto.value)
                }

                val errorMessage = response.getErrorMessage()

                if (response.isSuccessful) {

                    navController.navigate(Screen.SignInScreen.route) {
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
                    prevState.copy(isLoading = false , launchedEffectKey = !prevState.launchedEffectKey)
                }
            }
        }
    }
    private fun Response<*>.getErrorMessage(): String? {
        val errorResponse: ForgetPasswordResponse? = errorBody()?.charStream()?.use {
            Gson().fromJson(it, ForgetPasswordResponse::class.java)
        }
        return errorResponse?.message
    }

    fun onInternetError(){
        _screenState.update { it.copy(
            message = "No Internet Connection",
            launchedEffectKey = !_screenState.value.launchedEffectKey) }
    }
}