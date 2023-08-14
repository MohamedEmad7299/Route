package com.ug.route.networking.dto_models

data class UserSignUpDTO(

    val name : String,
    val email : String,
    val password : String,
    val rePassword : String = password,
    val phone : String
)