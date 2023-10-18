package com.ug.route.networking.body_models

data class UserSignUpBody(

    val name : String,
    val email : String,
    val password : String,
    val rePassword : String = password,
    val phone : String
)