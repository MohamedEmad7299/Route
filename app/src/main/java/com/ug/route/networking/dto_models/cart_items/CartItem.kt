package com.ug.route.networking.dto_models.cart_items


import com.google.gson.annotations.SerializedName

data class CartItem(
    @SerializedName("count")
    var count: Int? = 0,
    @SerializedName("_id")
    val id: String? = "",
    @SerializedName("price")
    val price: Int? = 0,
    @SerializedName("product")
    val product: ProductX? = ProductX()
)