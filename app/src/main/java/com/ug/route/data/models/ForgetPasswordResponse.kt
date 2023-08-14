package com.ug.route.data.models


import com.google.gson.annotations.SerializedName

data class ForgetPasswordResponse(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("statusMsg")
    val statusMsg: String? = null
)