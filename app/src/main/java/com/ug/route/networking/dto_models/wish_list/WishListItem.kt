package com.ug.route.networking.dto_models.wish_list


import com.google.gson.annotations.SerializedName

data class WishListItem(
    @SerializedName("availableColors")
    val availableColors: List<Any?>? = null,
    @SerializedName("brand")
    val brand: Brand? = null,
    @SerializedName("category")
    val category: Category? = null,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("_id")
    val _id: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("imageCover")
    val imageCover: String? = null,
    @SerializedName("images")
    val images: List<String?>? = null,
    @SerializedName("price")
    val price: Int? = null,
    @SerializedName("priceAfterDiscount")
    val priceAfterDiscount: Int? = null,
    @SerializedName("quantity")
    val quantity: Int? = null,
    @SerializedName("ratingsAverage")
    val ratingsAverage: Double? = null,
    @SerializedName("ratingsQuantity")
    val ratingsQuantity: Int? = null,
    @SerializedName("slug")
    val slug: String? = null,
    @SerializedName("sold")
    val sold: Int? = null,
    @SerializedName("subcategory")
    val subcategory: List<Subcategory?>? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null,
    @SerializedName("__v")
    val v: Int? = null
)