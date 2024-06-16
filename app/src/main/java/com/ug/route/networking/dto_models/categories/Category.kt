package com.ug.route.networking.dto_models.categories


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("_id")
    val id: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("slug")
    val slug: String? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null
)