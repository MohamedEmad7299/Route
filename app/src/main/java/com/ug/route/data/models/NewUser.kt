package com.ug.route.data.models


import com.google.gson.annotations.SerializedName

data class NewUser(
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("role")
    val role: String? = null
)