package com.ug.route.ui.cart_screen

import com.ug.route.networking.dto_models.cart_items.CartItem

data class CartState(

    val cartItems : List<CartItem>,
    val message: String,
    val launchedEffectKey: Boolean,
    val isLoadingButton: Boolean,
    val isLoadingScreen: Boolean,
    val totalPrice: Int,
    val isSearchBarActive: Boolean,
    val focused: Boolean
)