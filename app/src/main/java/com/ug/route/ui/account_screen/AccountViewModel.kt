package com.ug.route.ui.account_screen

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.ug.route.R
import com.ug.route.data.database.entities.UserData
import com.ug.route.data.repositories.Repository
import com.ug.route.utils.Screen
import com.ug.route.utils.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@SuppressLint("SuspiciousIndentation")
@HiltViewModel
class AccountViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _screenState = MutableStateFlow(

        AccountState(
            firstName = "",
            userData = UserData(0,"","","","",""),
            message = "",
            launchedEffectKey = false,
            isLoading = false,
            visibility = false,
            isNameError = false,
            isEmailError = false,
            isPasswordError = false,
            isPhoneError = false
        )
    )

    val screenState = _screenState.asStateFlow()

    private var savedUser : UserData? = null

    init {

        viewModelScope.launch {

            savedUser = (repository.getUserByEmail(SharedPreferences.loggedEmail ?: ""))
            if (savedUser != null){
                _screenState.update { it.copy(
                    firstName = savedUser!!.name,
                    userData = savedUser!!
                ) }
            }
        }
    }

    fun onChangeName(newName : String){

        _screenState.update { it.copy(userData = _screenState.value.userData.copy(name = newName)) }
    }

    fun onChangeEmail(newEmail : String){

        _screenState.update { it.copy(userData = _screenState.value.userData.copy(email = newEmail)) }
    }

    fun onChangePassword(newPassword : String){

        _screenState.update { it.copy(userData = _screenState.value.userData.copy(password = newPassword)) }
    }

    fun onChangePhone(newPhone : String){

        _screenState.update { it.copy(userData = _screenState.value.userData.copy(phone = newPhone)) }
    }

    fun onChangeAddress(newAddress : String){

        _screenState.update { it.copy(userData = _screenState.value.userData.copy(address = newAddress)) }
    }

    fun onChangeVisibility(visibility : Boolean) : Int{

        return if (visibility) R.drawable.visibility
        else R.drawable.visibility_off

    }

    fun updatePasswordVisibility(){

        _screenState.update { it.copy(visibility = !_screenState.value.visibility) }
    }

    fun saveEdit(){

        if (checkInputError()) return
        viewModelScope.launch {

            _screenState.update { prevState ->
                prevState.copy(isLoading = true)
            }

            try {

                if (savedUser != _screenState.value.userData){
                    repository.updateUser(_screenState.value.userData)
                    _screenState.update { prevState ->
                        prevState.copy(message = "Done")
                    }
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

    private fun checkInputError(): Boolean {
        val user = _screenState.value.userData
        _screenState.update { prevState ->
            prevState.copy(
                isPhoneError = user.phone.isEmpty(),
                isNameError = user.name.isEmpty() || user.name.length < 3,
                isPasswordError = user.password.isEmpty(),
                isEmailError = user.email.isEmpty() || !("@" in user.email && "." in user.email)
            )
        }
        return _screenState.value.isPhoneError ||
                _screenState.value.isNameError ||
                _screenState.value.isPasswordError ||
                _screenState.value.isEmailError
    }

    fun signOut(navController: NavController){

        SharedPreferences.loggedEmail = ""
        navController.navigate(Screen.SignInScreen.route){
            popUpTo(navController.graph.id){
                inclusive = true
            }
        }
    }
}