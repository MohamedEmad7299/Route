package com.ug.route.networking.dto_models


import com.google.gson.annotations.SerializedName

data class ValidationCodeDTO(
    @SerializedName("resetCode")
    val resetCode: String? = null
)