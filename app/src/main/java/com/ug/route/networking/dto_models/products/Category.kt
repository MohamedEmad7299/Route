package com.ug.route.networking.dto_models.products


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("_id")
    val id: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("slug")
    val slug: String? = null
)