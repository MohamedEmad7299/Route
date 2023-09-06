package com.ug.route.ui.account_screen

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController

@Composable
fun AccountScreen(navController : NavController){

    AccountContent()
}

@Composable
fun AccountContent() {

    ConstraintLayout(
        Modifier.background(Color.Gray)
    ){

    }
}