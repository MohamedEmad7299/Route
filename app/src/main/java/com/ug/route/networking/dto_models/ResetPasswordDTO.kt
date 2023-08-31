package com.ug.route.networking.dto_models

data class ResetPasswordDTO(
    val email: String,
    val newPassword: String
)