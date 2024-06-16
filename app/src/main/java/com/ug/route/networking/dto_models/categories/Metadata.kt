package com.ug.route.networking.dto_models.categories


import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("currentPage")
    val currentPage: Int? = null,
    @SerializedName("limit")
    val limit: Int? = null,
    @SerializedName("numberOfPages")
    val numberOfPages: Int? = null
)