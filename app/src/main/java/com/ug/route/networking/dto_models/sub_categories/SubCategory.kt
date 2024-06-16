package com.ug.route.networking.dto_models.sub_categories


import com.google.gson.annotations.SerializedName

data class  SubCategory(
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("_id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("slug")
    val slug: String? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null
)