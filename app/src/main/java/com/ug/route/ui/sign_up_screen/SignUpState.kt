package com.ug.route.ui.sign_up_screen

data class SignUpState(

    val message : String,
    val launchedEffectKey : Boolean,
    val isLoading : Boolean,
    val passwordVisibility : Boolean,
    val rePasswordVisibility : Boolean,
    val isEmailError : Boolean,
    val isPasswordError : Boolean,
    val isRePasswordError : Boolean,
    val isNameError : Boolean,
    val isPhoneError : Boolean,
)
