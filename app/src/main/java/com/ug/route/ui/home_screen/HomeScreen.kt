package com.ug.route.ui.home_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ug.route.ui.design_matrials.text.SearchBarAndCart
import com.ug.route.ui.design_matrials.text.SmallLogo

@Composable
fun HomeScreen(){

    HomeContent()

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(Color.Transparent,darkIcons = true)
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeContent(){

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val(logo,
            searchBarAndCart) = createRefs()

        SmallLogo(
            modifier = Modifier
                .constrainAs(logo){
                start.linkTo(parent.start,16.dp)
                top.linkTo(parent.top,16.dp)
            }
        )

        SearchBarAndCart(
            modifier = Modifier.constrainAs(searchBarAndCart){
                start.linkTo(parent.start)
                top.linkTo(logo.top,32.dp)
            },
            isError = false,
            value = "",
            onValueChange = {}) {
        }
    }
}