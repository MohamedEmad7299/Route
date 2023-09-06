package com.ug.route.ui.categories_screen

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController


@Composable
fun CategoriesScreen(navController : NavController){

    CategoriesContent()
}

@Composable
fun CategoriesContent() {

    ConstraintLayout(
        Modifier.background(Color.Black)
    ){

    }
}
