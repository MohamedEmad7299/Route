package com.ug.route.ui.favourite_screen

import com.ug.route.networking.dto_models.cart_items.CartItem
import com.ug.route.networking.dto_models.wish_list.WishListItem

data class FavouriteState(

    val favouriteProducts : List<WishListItem?>,
    val launchedEffectKey: Boolean,
    val isSearchBarActive: Boolean,
    val focused: Boolean,
    val cartItems: List<CartItem>,
    val isLoading: Boolean,
    val message: String
)