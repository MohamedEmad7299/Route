package com.ug.route.ui.sign_up_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.ug.route.R
import com.ug.route.data.models.FailResponse
import com.ug.route.data.repositories.Repository
import com.ug.route.networking.dto_models.UserSignUpDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _user = MutableStateFlow(UserSignUpDTO("","","","",""))
    val user = _user.asStateFlow()

    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()

    private val _launchedEffectKey = MutableStateFlow(false)
    val launchedEffectKey = _launchedEffectKey.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _passwordVisibility = MutableStateFlow(false)
    val passwordVisibility = _passwordVisibility.asStateFlow()

    private val _rePasswordVisibility = MutableStateFlow(false)
    val rePasswordVisibility = _rePasswordVisibility.asStateFlow()

    private val _isPasswordError = MutableStateFlow(false)
    val isPasswordError = _isPasswordError.asStateFlow()

    private val _isEmailError = MutableStateFlow(false)
    val isEmailError = _isEmailError.asStateFlow()

    private val _isNameError = MutableStateFlow(false)
    val isNameError = _isNameError.asStateFlow()

    private val _isPhoneError = MutableStateFlow(false)
    val isPhoneError = _isPhoneError.asStateFlow()

    private val _isRePasswordError = MutableStateFlow(false)
    val isRePasswordError = _isRePasswordError.asStateFlow()

    fun onChangePassword(newPassword : String){

        _user.update { it.copy(password = newPassword) }
    }

    fun onChangePasswordVisibility(){

        _passwordVisibility.update { !_passwordVisibility.value }
    }

    fun onChangeRePasswordVisibility(){

        _rePasswordVisibility.update { !_rePasswordVisibility.value }
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

        _isPhoneError.value = user.phone.isEmpty()
        _isNameError.value = user.name.isEmpty() || user.name.length < 3
        _isPasswordError.value = user.password.isEmpty()
        _isRePasswordError.value = user.rePassword.isEmpty() || user.password != user.rePassword

        _isEmailError.value = user.email.isEmpty() || !("@" in user.email && "." in user.email)

        return isPhoneError.value ||
                isNameError.value ||
                isPasswordError.value ||
                isEmailError.value ||
                isRePasswordError.value
    }

    fun signUp() {

        if (checkInputError()) return

        viewModelScope.launch {

            _isLoading.value = true

            val response = repository.signUp(user.value)
            val errorResponse: FailResponse? = response.errorBody()?.charStream()?.use {
                Gson().fromJson(it, FailResponse::class.java)
            }

            if (response.isSuccessful) {
                _message.value = "Account Created Successfully"
            } else {
                val errorMessage = when (response.code()) {
                    409 -> errorResponse?.message
                    else -> errorResponse?.errors?.msg
                }
                _message.value = errorMessage ?: "An error occurred"
            }

            _launchedEffectKey.value = !launchedEffectKey.value
            _isLoading.value = false
        }
    }
}