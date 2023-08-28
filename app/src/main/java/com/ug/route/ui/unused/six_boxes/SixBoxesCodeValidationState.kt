package com.ug.route.ui.unused.six_boxes

import com.ug.route.utils.ResetCode

data class SixBoxesCodeValidationState(

    val resetCode: ResetCode,
    val email : String,
    val message : String,
    val launchedEffectKey : Boolean,
    val isLoading : Boolean,
    val isError : Boolean,
    val isClickable : Boolean
)