package com.ug.route.ui.home_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ug.route.ui.design_matrials.text.SearchBarAndCart
import com.ug.route.ui.design_matrials.text.SliderBanner
import com.ug.route.ui.design_matrials.text.SmallLogo
import com.ug.route.ui.design_matrials.text.Text18

@Composable
fun HomeScreen(navController : NavController){

    HomeContent()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Preview(showSystemUi = true)
@Composable
fun HomeContent(){

    val systemUiController = rememberSystemUiController()

    ConstraintLayout(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {

        SideEffect {
            systemUiController.setStatusBarColor(Color.White,darkIcons = true)
        }

        val(logo,
            searchBarAndCart,
            slider,
            categoriesText,) = createRefs()

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

        SliderBanner(
            modifier = Modifier.constrainAs(slider){
                top.linkTo(searchBarAndCart.bottom)
            }
        )

        Text18(
            modifier = Modifier.constrainAs(categoriesText){
                start.linkTo(parent.start,16.dp)
                top.linkTo(slider.bottom,24.dp)
            },
            text = "Categories",
            color = Color(0xFF06004F)
        )
    }
}