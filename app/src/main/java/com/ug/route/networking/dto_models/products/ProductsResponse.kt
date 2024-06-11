package com.ug.route.networking.dto_models.products


import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("data")
    val `data`: List<Product?>? = null,
    @SerializedName("metadata")
    val metadata: Metadata? = null,
    @SerializedName("results")
    val results: Int? = null
)