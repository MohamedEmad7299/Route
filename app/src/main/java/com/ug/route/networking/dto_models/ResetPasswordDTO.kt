package com.ug.route.networking.dto_models


import com.google.gson.annotations.SerializedName

data class ResetPasswordDTO(
    @SerializedName("email")
    val email: String? = null
)