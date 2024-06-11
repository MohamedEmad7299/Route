package com.ug.route.networking.dto_models.cart_items


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("cartOwner")
    val cartOwner: String? = "",
    @SerializedName("createdAt")
    val createdAt: String? = "",
    @SerializedName("_id")
    val id: String? = "",
    @SerializedName("products")
    val cartItems: List<CartItem>? = listOf(),
    @SerializedName("totalCartPrice")
    val totalCartPrice: Int? = 0,
    @SerializedName("updatedAt")
    val updatedAt: String? = "",
    @SerializedName("__v")
    val v: Int? = 0
)