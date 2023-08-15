package com.ug.route.ui.reset_password_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.ug.route.data.models.ResetPasswordResponse
import com.ug.route.data.repositories.Repository
import com.ug.route.networking.dto_models.ResetPasswordDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()

    private val _isEmailError = MutableStateFlow(false)
    val isEmailError = _isEmailError.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _launchedEffectKey = MutableStateFlow(false)
    val launchedEffectKey = _launchedEffectKey.asStateFlow()

    fun onChangeEmail(newEmail : String){

        _email.update { newEmail }
    }


    private fun checkInputError(): Boolean {

        val email = _email.value

        _isEmailError.value = email.isEmpty() || !("@" in email && "." in email)

        return isEmailError.value
    }

    fun resetPassword(){

        if (checkInputError()) return
        viewModelScope.launch {

            _isLoading.value = true

            val response = repository.resetPassword(ResetPasswordDTO(email = email.value))
            val errorResponse: ResetPasswordResponse? = response.errorBody()?.charStream()?.use {
                Gson().fromJson(it, ResetPasswordResponse::class.java)
            }

            if (response.isSuccessful) {
                _message.value = "Reset code sent to your email"
            } else {
                val errorMessage = errorResponse?.message
                _message.value = errorMessage ?: "An error occurred"
            }

            _launchedEffectKey.value = !launchedEffectKey.value
            _isLoading.value = false
        }
    }

}
