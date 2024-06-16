package com.ug.route.ui.code_validation_screen

data class CodeValidationState(

    val resetCode: String,
    val email : String,
    val message : String,
    val launchedEffectKey : Boolean,
    val isLoading : Boolean,
    val isError : Boolean,
    val isClickable : Boolean
)