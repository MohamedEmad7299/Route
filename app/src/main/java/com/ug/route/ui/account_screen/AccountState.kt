package com.ug.route.ui.account_screen

import com.ug.route.data.database.entities.UserEntity

data class AccountState(
    val fullName : String,
    val userEntity : UserEntity,
    val message : String,
    val launchedEffectKey : Boolean,
    val isLoading : Boolean,
    val visibility : Boolean,
    val isNameError : Boolean,
    val isEmailError : Boolean,
    val isPasswordError : Boolean,
    val isPhoneError : Boolean
)
