package com.ug.route.networking.dto_models


import com.google.gson.annotations.SerializedName

data class CodeValidationResponse(
    @SerializedName("status")
    val status: String? = null
)