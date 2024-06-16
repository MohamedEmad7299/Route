package com.ug.route.networking.dto_models.wish_list


import com.google.gson.annotations.SerializedName

data class WishListResponse(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("data")
    val `data`: List<WishListItem?>? = null,
    @SerializedName("status")
    val status: String? = null
)