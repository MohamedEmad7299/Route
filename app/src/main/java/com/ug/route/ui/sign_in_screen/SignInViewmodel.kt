package com.ug.route.ui.sign_in_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.ug.route.R
import com.ug.route.data.models.FailResponse
import com.ug.route.networking.dto_models.UserSignInDTO
import com.ug.route.data.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()

    private val _launchedEffectKey = MutableStateFlow(false)
    val launchedEffectKey = _launchedEffectKey.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isEmailError = MutableStateFlow(false)
    val isEmailError = _isEmailError.asStateFlow()

    private val _isPasswordError = MutableStateFlow(false)
    val isPasswordError = _isPasswordError.asStateFlow()

    private val _passwordVisibility = MutableStateFlow(false)
    val passwordVisibility = _passwordVisibility.asStateFlow()

    private val _user = MutableStateFlow(UserSignInDTO("",""))
    val user = _user.asStateFlow()

    fun onChangePassword(newPassword : String){

        _user.update { it.copy(password = newPassword) }
    }
    fun onChangeEmail(newEmail : String){

        _user.update { it.copy(email = newEmail) }
    }

    fun signIn(){

        if (checkInputError()) return

        viewModelScope.launch {

            _isLoading.update { true }
            val response = repository.signIn(user.value)
            val errorResponse: FailResponse? = response.errorBody()?.charStream()?.use {
                Gson().fromJson(it, FailResponse::class.java)
            }

            if (response.isSuccessful) {
                _message.value = "Welcome"
            } else {
                val errorMessage = when (response.code()) {
                    401 -> errorResponse?.message
                    else -> errorResponse?.errors?.msg
                }
                _message.value = errorMessage ?: "An error occurred"
            }
            _launchedEffectKey.update { !launchedEffectKey.value }
            _isLoading.update { false }
        }
    }

    fun onChangePasswordVisibility(passwordVisibility : Boolean) : Int{

        return if (passwordVisibility) R.drawable.visibility
        else R.drawable.visibility_off

    }

    fun onClickVisibilityIcon(){
        _passwordVisibility.update { !_passwordVisibility.value }
    }

    private fun checkInputError() : Boolean{

        val user = user.value
        _isPasswordError.value = user.password.isEmpty()
        _isEmailError.value = user.email.isEmpty() || !("@" in user.email && "." in user.email)

        return _isEmailError.value || _isPasswordError.value
    }
}