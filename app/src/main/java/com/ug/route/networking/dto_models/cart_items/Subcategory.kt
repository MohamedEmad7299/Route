package com.ug.route.networking.dto_models.cart_items


import com.google.gson.annotations.SerializedName

data class Subcategory(
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("_id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("slug")
    val slug: String? = null
)