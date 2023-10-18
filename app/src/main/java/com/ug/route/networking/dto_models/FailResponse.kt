package com.ug.route.networking.dto_models


import com.google.gson.annotations.SerializedName

data class FailResponse(

    @SerializedName("statusMsg")
    val statusMsg: String? = "",
    @SerializedName("message")
    val message: String? = "",
    @SerializedName("errors")
    val errors: Errors? = Errors()
)