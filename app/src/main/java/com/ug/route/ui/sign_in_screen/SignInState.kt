package com.ug.route.ui.sign_in_screen

data class SignInState(

    val message : String,
    val launchedEffectKey : Boolean,
    val isLoading : Boolean,
    val isEmailError : Boolean,
    val isPasswordError : Boolean,
    val passwordVisibility : Boolean
)
