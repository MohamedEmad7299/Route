package com.ug.route.networking.dto_models.products


import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("data")
    val `data`: List<Data?>? = null,
    @SerializedName("metadata")
    val metadata: Metadata? = null,
    @SerializedName("results")
    val results: Int? = null
)