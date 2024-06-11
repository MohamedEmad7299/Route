package com.ug.route.networking.dto_models.cart_items


import com.google.gson.annotations.SerializedName

data class ProductX(
    @SerializedName("brand")
    val brand: Brand? = null,
    @SerializedName("category")
    val category: Category? = null,
    @SerializedName("_id")
    val _id: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("imageCover")
    val imageCover: String? = null,
    @SerializedName("quantity")
    val quantity: Int? = null,
    @SerializedName("ratingsAverage")
    val ratingsAverage: Double? = null,
    @SerializedName("subcategory")
    val subcategory: List<Subcategory?>? = null,
    @SerializedName("title")
    val title: String? = null
)