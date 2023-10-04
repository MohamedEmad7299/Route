package com.ug.route.ui.no_internet_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ug.route.R
import com.ug.route.ui.theme.Gray80

@Preview(showSystemUi = true)
@Composable
fun NoInternetScreen(){

    NoInternetContent{

    }
}


@Composable
fun NoInternetContent(onClickTryAgain : () -> Unit){

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.no_internet))

    val systemUiController = rememberSystemUiController()

    SideEffect {

        systemUiController.setStatusBarColor(Color.White,darkIcons = true)
    }

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
                .constrainAs(animation) {
                    top.linkTo(parent.top, 64.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
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

        ClickableText(
            text = AnnotatedString("Try again!"),
            modifier = Modifier
                .constrainAs(tryAgainButton) {
                    top.linkTo(instructionsText.bottom,32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(600),
                color = Color.Black,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline
            )
        ){
            onClickTryAgain()
        }
    }
}

