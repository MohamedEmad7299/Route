package com.ug.route.ui.reset_password_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.gson.Gson
import com.ug.route.data.models.ResetPasswordResponse
import com.ug.route.data.repositories.Repository
import com.ug.route.networking.dto_models.ResetPasswordDTO
import com.ug.route.utils.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _screenState = MutableStateFlow(
        ResetPasswordState(
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

    fun resetPassword(navController: NavController){

        if (checkInputError()) return
        viewModelScope.launch {

            _screenState.update { it.copy(isLoading = true) }

            val response = repository.resetPassword(ResetPasswordDTO(email = email.value))
            val errorMessage = response.getErrorMessage()

            if (response.isSuccessful) navController.navigate("${Screen.CodeValidationScreen.route}/${_email.value}"){
                popUpTo(navController.graph.id){
                    inclusive = true
                }
            }
             else _screenState.update { it.copy(message = errorMessage ?: "An error occurred") }

            _screenState.update {it.copy(launchedEffectKey = !_screenState.value.launchedEffectKey,isLoading = false)}
        }
    }

    private fun Response<*>.getErrorMessage(): String? {
        val errorResponse: ResetPasswordResponse? = errorBody()?.charStream()?.use {
            Gson().fromJson(it, ResetPasswordResponse::class.java)
        }
        return errorResponse?.message
    }

    fun onInternetError(){
        _screenState.update { it.copy(
            message = "No Internet Connection",
            launchedEffectKey = !_screenState.value.launchedEffectKey) }
    }
}
