package com.ug.route.ui.code_validation_screen

import com.ug.route.data.models.ResetCode

data class CodeValidationState(

    val resetCode: ResetCode,
    val email : String,
    val message : String,
    val launchedEffectKey : Boolean,
    val isLoading : Boolean,
    val isError : Boolean,
    val isClickable : Boolean
)