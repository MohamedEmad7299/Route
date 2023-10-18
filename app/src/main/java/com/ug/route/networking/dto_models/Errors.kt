package com.ug.route.networking.dto_models


import com.google.gson.annotations.SerializedName

data class Errors(
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("msg")
    val msg: String? = null,
    @SerializedName("param")
    val `param`: String? = null,
    @SerializedName("value")
    val value: String? = null
)