package com.ug.route.ui.favourite_screen

import com.ug.route.data.database.entities.CartEntity
import com.ug.route.data.database.entities.FavouriteEntity

data class FavouriteState(

    val favouriteProducts : List<FavouriteEntity>,
    val launchedEffectKey: Boolean,
    val isSearchBarActive: Boolean,
    val focused: Boolean,
    val cartItems: List<CartEntity>
)