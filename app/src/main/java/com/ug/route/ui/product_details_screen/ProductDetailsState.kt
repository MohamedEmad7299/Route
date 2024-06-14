package com.ug.route.ui.product_details_screen


data class ProductDetailsState(

    val productId: String,
    val message: String,
    val launchedEffectKey: Boolean,
    val counter: Int,
    val isFavourite: Boolean,
    val isLoading: Boolean
)
