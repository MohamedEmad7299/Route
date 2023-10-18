package com.ug.route.networking.body_models


import com.google.gson.annotations.SerializedName

data class ValidationCodeBody(
    @SerializedName("resetCode")
    val resetCode: String? = null
)