package com.ug.route.ui.forget_password_screen

data class ForgetPasswordState(

    val message : String,
    val launchedEffectKey : Boolean,
    val isLoading : Boolean,
    val isEmailError : Boolean
)
