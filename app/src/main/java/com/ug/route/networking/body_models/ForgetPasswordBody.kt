package com.ug.route.networking.body_models


import com.google.gson.annotations.SerializedName

data class ForgetPasswordBody(
    @SerializedName("email")
    val email: String? = null
)