package com.ug.route.ui.home_screen

import com.ug.route.data.models.Category

data class HomeState(

    val searchInput : String,
    val categories : List<Category?>,
    val message : String,
    val launchedEffectKey : Boolean,
    val isLoading : Boolean
)
