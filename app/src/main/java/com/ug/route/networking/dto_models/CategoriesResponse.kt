package com.ug.route.networking.dto_models


import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("data")
    val `data`: List<Category?>? = null,
    @SerializedName("metadata")
    val metadata: Metadata? = null,
    @SerializedName("results")
    val results: Int? = null
)