package com.ug.route.networking.dto_models.wish_list


import com.google.gson.annotations.SerializedName

data class AddProductToWishListResponse(
    @SerializedName("data")
    val `data`: List<String?>? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("status")
    val status: String? = null
)