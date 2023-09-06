package com.ug.route.ui.design_matrials.text.bottomNav

import com.ug.route.R
import com.ug.route.utils.Screen

sealed class BottomNavScreen(

    val route: String,
    val icon : Int,
){
    object Home : BottomNavScreen(
        route = Screen.HomeScreen.route,
        icon = R.drawable.home
    )
    object Categorise : BottomNavScreen(
        route = Screen.CategoriesScreen.route,
        icon = R.drawable.category
    )

    object Favourite : BottomNavScreen(
        route = Screen.FavouriteScreen.route,
        icon = R.drawable.heart
    )

    object Account : BottomNavScreen(
        route = Screen.AccountScreen.route,
        icon = R.drawable.user
    )
}
