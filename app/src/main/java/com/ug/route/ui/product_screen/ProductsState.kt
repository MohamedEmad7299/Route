package com.ug.route.ui.product_screen

import com.ug.route.networking.dto_models.products.Product

data class ProductsState(

    val launchedEffectKey: Boolean,
    val subCategoryId: String,
    val products: List<Product>,
    val message: String,
    val isLoading: Boolean
)
