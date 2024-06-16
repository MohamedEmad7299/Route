package com.ug.route.networking.dto_models.cart_items


import com.google.gson.annotations.SerializedName

data class CartItemsResponse(
    @SerializedName("data")
    val `data`: Data? = Data(),
    @SerializedName("numOfCartItems")
    val numOfCartItems: Int? = 0,
    @SerializedName("status")
    val status: String? = ""
)