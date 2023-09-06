package com.ug.route.data.models


import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("data")
    val `data`: List<Data?>? = null,
    @SerializedName("metadata")
    val metadata: Metadata? = null,
    @SerializedName("results")
    val results: Int? = null
)