package com.ug.route.ui.reset_password_screen

data class ResetPasswordState(

    val message : String,
    val launchedEffectKey : Boolean,
    val isLoading : Boolean,
    val passwordVisibility : Boolean,
    val rePasswordVisibility : Boolean,
    val isPasswordError : Boolean,
    val isRePasswordError : Boolean,
)


