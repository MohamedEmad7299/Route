package com.ug.route.networking.dto_models.sub_categories


import com.google.gson.annotations.SerializedName

data class SubCategoriesResponse(
    @SerializedName("data")
    val `data`: List<SubCategory?>? = null,
    @SerializedName("metadata")
    val metadata: Metadata? = null,
    @SerializedName("results")
    val results: Int? = null
)