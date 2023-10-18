package com.ug.route.ui.code_validation_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.gson.Gson
import com.ug.route.networking.dto_models.ForgetPasswordResponse
import com.ug.route.data.repositories.Repository
import com.ug.route.networking.body_models.ForgetPasswordBody
import com.ug.route.networking.body_models.ValidationCodeBody
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
class CodeValidationViewModel  @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _screenState = MutableStateFlow(
        CodeValidationState(
            resetCode = "",
            message = "",
            isError = false,
            isLoading = false,
            email = checkNotNull(savedStateHandle["email"]),
            launchedEffectKey = false,
            isClickable = true)
    )

    val screenState = _screenState.asStateFlow()

    fun onChangeCode(newCode: String) {

        _screenState.update { it.copy(resetCode = newCode) }
    }

    fun updateMessageAndKeyOnClick(isClickable: Boolean) {
        val newMessage = if (isClickable) "we've sent the code to your email" else _screenState.value.message
        _screenState.update { it.copy(message = newMessage, launchedEffectKey = !it.launchedEffectKey) }
    }

    fun disableClickHere(){
        _screenState.update { it.copy(isClickable = false) }
    }

    fun resetPassword() {

        viewModelScope.launch {

            try {
                withTimeout(5000L) {
                    repository.forgetPassword(ForgetPasswordBody(email = _screenState.value.email))
                }
            } catch (e: TimeoutCancellationException) {
                _screenState.update { it.copy(message = "Request timed out") }
            } catch (e: Exception) {
                _screenState.update { it.copy(message = "An error occurred") }
            }
        }
    }


    fun codeValidation(navController : NavController) {

        if (checkInputError()) return

        viewModelScope.launch {
            _screenState.update { it.copy(isLoading = true) }

            try {
                val response = withTimeout(5000L) {
                    repository.codeValidation(ValidationCodeBody(_screenState.value.resetCode))
                }
                val errorMessage = response.getErrorMessage()

                if (response.isSuccessful) {

                    navController.navigate("${Screen.ResetPasswordScreen.route}/${_screenState.value.email}") {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
                else _screenState.update { it.copy(message = errorMessage ?: "An error occurred") }

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

    private fun checkInputError(): Boolean {

        val resetCode =  _screenState.value.resetCode
        val isError = resetCode.isEmpty() || !resetCode.all { it.isDigit() }
        _screenState.update {it.copy(isError = isError)}
        return  isError
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