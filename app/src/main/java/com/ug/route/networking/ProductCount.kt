package com.ug.route.networking


import com.google.gson.annotations.SerializedName

data class ProductCount(
    @SerializedName("count")
    val count: String? = null
)