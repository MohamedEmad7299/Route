package com.ug.route.ui.home_screen

import com.ug.route.networking.dto_models.categories.Category

data class HomeState(

    val categories: List<Category>,
    val message: String,
    val launchedEffectKey: Boolean,
    val isLoading: Boolean,
    val isSearchBarActive: Boolean,
    val focused: Boolean
)
