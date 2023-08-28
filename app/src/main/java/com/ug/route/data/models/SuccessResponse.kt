package com.ug.route.data.models


import com.google.gson.annotations.SerializedName

data class SuccessResponse(
    @SerializedName("message")
    val message: String? = "",
    @SerializedName("token")
    val token: String? = "",
    @SerializedName("user")
    val user: NewUser? = NewUser()
)