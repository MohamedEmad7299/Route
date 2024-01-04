package com.ug.route.ui.favourite_screen

import com.ug.route.data.database.entities.FavouriteEntity

data class FavouriteState(

    val query: String,
    val favouriteProducts : List<FavouriteEntity>,
    val message: String,
    val launchedEffectKey: Boolean,
    val isSearchBarActive: Boolean,
    val focused: Boolean
)