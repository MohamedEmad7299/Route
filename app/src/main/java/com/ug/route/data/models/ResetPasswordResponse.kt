package com.ug.route.data.models


import com.google.gson.annotations.SerializedName

data class ResetPasswordResponse(

    @SerializedName("statusMsg")
    val statusMsg: String? = null,
    @SerializedName("message")
    val message: String? = null
)