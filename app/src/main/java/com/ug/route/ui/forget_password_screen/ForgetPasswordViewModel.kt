package com.ug.route.ui.forget_password_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.gson.Gson
import com.ug.route.data.models.ForgetPasswordResponse
import com.ug.route.data.repositories.Repository
import com.ug.route.networking.dto_models.ForgetPasswordDTO
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
class ForgetPasswordViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _screenState = MutableStateFlow(
        ForgetPasswordState(
        message = "",
        isEmailError = false,
        isLoading = false,
        launchedEffectKey = false)
    )

    val screenState = _screenState.asStateFlow()

    fun onChangeEmail(newEmail : String){

        _email.update { newEmail }
    }

    private fun checkInputError(): Boolean {

        val email = _email.value

        _screenState.update { it.copy(isEmailError = email.isEmpty() || !("@" in email && "." in email)) }

        return _screenState.value.isEmailError
    }

    fun resetPassword(navController: NavController) {

        if (checkInputError()) return

        viewModelScope.launch {

            _screenState.update { it.copy(isLoading = true) }

            try {
                val response = withTimeout(5000L) {
                    repository.forgetPassword(ForgetPasswordDTO(email = email.value))
                }
                val errorMessage = response.getErrorMessage()

                if (response.isSuccessful) {
                    navController.navigate("${Screen.CodeValidationScreen.route}/${_email.value}") {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                } else {
                    _screenState.update { it.copy(message = errorMessage ?: "An error occurred") }
                }
            } catch (e: TimeoutCancellationException) {
                _screenState.update { it.copy(message = "Request timed out") }
            } catch (e: Exception) {
                _screenState.update { it.copy(message = "An error occurred") }
            } finally {
                _screenState.update {
                    it.copy(
                        launchedEffectKey = !it.launchedEffectKey,
                        isLoading = false
                    )
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
