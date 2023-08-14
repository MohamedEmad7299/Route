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

    val message = MutableStateFlow("")
    val launchedEffectKey = MutableStateFlow(false)
    val isLoading = MutableStateFlow(false)
    val passwordVisibility = MutableStateFlow(false)
    val rePasswordVisibility = MutableStateFlow(false)
    val isPasswordError = MutableStateFlow(false)
    val isEmailError = MutableStateFlow(false)
    val isNameError = MutableStateFlow(false)
    val isPhoneError = MutableStateFlow(false)
    val isRePasswordError = MutableStateFlow(false)

    fun onChangePassword(newPassword : String){

        _user.update { it.copy(password = newPassword) }
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

        isPhoneError.value = user.phone.isEmpty()
        isNameError.value = user.name.isEmpty()
        isPasswordError.value = user.password.isEmpty()
        isRePasswordError.value = user.rePassword.isEmpty() || user.password != user.rePassword

        isEmailError.value = user.email.isEmpty() || !("@" in user.email && "." in user.email)

        return isPhoneError.value ||
                isNameError.value ||
                isPasswordError.value ||
                isEmailError.value ||
                isRePasswordError.value
    }

    fun signUp() {

        if (checkInputError()) return

        viewModelScope.launch {

            isLoading.value = true

            val response = repository.signUp(user.value)
            val errorResponse: FailResponse? = response.errorBody()?.charStream()?.use {
                Gson().fromJson(it, FailResponse::class.java)
            }

            if (response.isSuccessful) {
                message.value = "Account Created Successfully"
            } else {
                val errorMessage = when (response.code()) {
                    409 -> errorResponse?.message
                    else -> errorResponse?.errors?.msg
                }
                message.value = errorMessage ?: "An error occurred"
            }

            launchedEffectKey.value = !launchedEffectKey.value
            isLoading.value = false
        }
    }
}