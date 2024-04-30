package com.ug.route.ui.product_screen

import com.ug.route.data.database.entities.CartEntity
import com.ug.route.data.database.entities.ProductEntity

data class ProductsState(

    val launchedEffectKey: Boolean,
    val productKey: String,
    val products: List<ProductEntity>,
    val cartItems: List<CartEntity>
)
