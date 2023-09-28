package com.ug.route.ui.favourite_screen

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController

@Composable
fun FavouriteScreen(navController : NavController){

    FavouriteContent()
}

@Composable
fun FavouriteContent() {

    ConstraintLayout(
        Modifier.background(Color.Red)
    ){

    }
}