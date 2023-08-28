package com.ug.route.ui.no_internet_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ug.route.R
import com.ug.route.ui.design_matrials.text.StandardButton
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.ui.theme.Gray80
import com.ug.route.utils.isInternetConnected

@Composable
fun NoInternetScreen(
    navController: NavController,
    viewModel: NoInternetViewModel = hiltViewModel()
){

    val context = LocalContext.current

    NoInternetContent{

        if (isInternetConnected(context)){
            navController.navigate(viewModel.previousRoute){
                popUpTo(navController.graph.id){
                    inclusive = true
                }
            }
        }
    }
}


@Composable
fun NoInternetContent(onClickTryAgain : () -> Unit){

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.no_internet))

    ConstraintLayout(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ){

        val (animation,
            instructionsText,
            tryAgainButton) = createRefs()

        LottieAnimation(
            modifier = Modifier
                .size(400.dp)
                .constrainAs(animation){
                    top.linkTo(parent.top,128.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end) },
            composition = composition,
            isPlaying = true
        )

        Text(
            text = "Try Checking the network cables, modem and router",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(300),
                color = Gray80,
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.constrainAs(instructionsText){
                top.linkTo(animation.bottom)
                start.linkTo(parent.start,16.dp)
                end.linkTo(parent.end,16.dp)}
        )

        StandardButton(
            buttonColor = DarkBlue,
            onClick = onClickTryAgain,
            modifier = Modifier.constrainAs(tryAgainButton){
                top.linkTo(instructionsText.bottom,48.dp)},
        ) {

            Text(
                text = "Try again",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(600),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

