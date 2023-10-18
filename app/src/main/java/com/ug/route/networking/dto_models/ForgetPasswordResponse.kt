package com.ug.route.networking.dto_models


import com.google.gson.annotations.SerializedName

data class ForgetPasswordResponse(

    @SerializedName("statusMsg")
    val statusMsg: String? = null,
    @SerializedName("message")
    val message: String? = null
)