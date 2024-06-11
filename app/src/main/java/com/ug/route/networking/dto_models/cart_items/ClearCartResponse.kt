package com.ug.route.networking.dto_models.cart_items


import com.google.gson.annotations.SerializedName

data class ClearCartResponse(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("statusMsg")
    val statusMsg: String? = null
)