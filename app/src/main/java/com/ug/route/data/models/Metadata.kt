package com.ug.route.data.models


import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("currentPage")
    val currentPage: Int? = null,
    @SerializedName("limit")
    val limit: Int? = null,
    @SerializedName("numberOfPages")
    val numberOfPages: Int? = null
)