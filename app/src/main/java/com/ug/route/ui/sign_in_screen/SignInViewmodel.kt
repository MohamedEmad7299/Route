package com.ug.route.ui.sign_in_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ug.route.R
import com.ug.route.data.models.User
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

    private val _passwordVisibility = MutableStateFlow(false)
    val passwordVisibility = _passwordVisibility.asStateFlow()

    private val _user = MutableStateFlow(User("",""))
    val user = _user.asStateFlow()

    fun onChangePassword(newPassword : String){

        _user.update { it.copy(password = newPassword) }
    }
    fun onChangeEmail(newEmail : String){

        _user.update { it.copy(email = newEmail) }
    }

    fun signIn(){

        viewModelScope.launch {

            isLoading.update { true }
            val response = repository.signIn(_user.value)
            message.update { response.message() }
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

    fun onChangeStatusCode(){

    }
}