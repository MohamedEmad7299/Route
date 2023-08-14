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


    val message = MutableStateFlow("")
    val launchedEffectKey = MutableStateFlow(false)
    val isLoading = MutableStateFlow(false)
    val isPasswordError = MutableStateFlow(false)
    val isEmailError = MutableStateFlow(false)


    private val _passwordVisibility = MutableStateFlow(false)
    val passwordVisibility = _passwordVisibility.asStateFlow()

    private val _userSignInDTO = MutableStateFlow(UserSignInDTO("",""))
    val user = _userSignInDTO.asStateFlow()

    fun onChangePassword(newPassword : String){

        _userSignInDTO.update { it.copy(password = newPassword) }
    }
    fun onChangeEmail(newEmail : String){

        _userSignInDTO.update { it.copy(email = newEmail) }
    }

    fun signIn(){

        if (checkInputError()) return

        viewModelScope.launch {
            isLoading.update { true }
            val response = repository.signIn(user.value)
            val errorResponse: FailResponse? = response.errorBody()?.charStream()?.use {
                Gson().fromJson(it, FailResponse::class.java)
            }

            if (response.isSuccessful) {
                message.value = "Welcome"
            } else {
                val errorMessage = when (response.code()) {
                    401 -> errorResponse?.message
                    else -> errorResponse?.errors?.msg
                }
                message.value = errorMessage ?: "An error occurred"
            }
            launchedEffectKey.update { !launchedEffectKey.value }
            isLoading.update { false }
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
        isPasswordError.value = user.password.isEmpty()
        isEmailError.value = user.email.isEmpty() || !("@" in user.email && "." in user.email)

        return isEmailError.value || isPasswordError.value
    }
}