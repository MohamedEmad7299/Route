package com.ug.route.ui.cart_screen

import com.ug.route.data.database.entities.CartEntity

data class CartState(

    val cartItems : List<CartEntity>,
    val message: String,
    val totalPrice: Int,
    val launchedEffectKey: Boolean,
    val isSearchBarActive: Boolean,
    val focused: Boolean
)