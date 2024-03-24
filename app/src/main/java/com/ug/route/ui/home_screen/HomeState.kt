package com.ug.route.ui.home_screen

import com.ug.route.data.database.entities.CategoryEntity

data class HomeState(

    val categories: List<CategoryEntity>,
    val message: String,
    val launchedEffectKey: Boolean,
    val isLoading: Boolean,
    val isSearchBarActive: Boolean,
    val focused: Boolean
)
