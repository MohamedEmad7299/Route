package com.ug.route.networking.body_models

data class ResetPasswordBody(
    val email: String,
    val newPassword: String
)