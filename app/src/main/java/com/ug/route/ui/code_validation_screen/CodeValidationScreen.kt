package com.ug.route.ui.code_validation_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ug.route.R
import com.ug.route.ui.design_matrials.text.Logo
import com.ug.route.ui.design_matrials.text.ResetBoxes
import com.ug.route.ui.design_matrials.text.StandardButton
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.ui.theme.Gray80

@Composable
fun CodeValidationScreen(){

    CodeValidationContent()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeValidationContent(){

    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            val (
                logo,
                resetPasswordText,
                instructionsText,
                boxes,
                errorMessage,
                continueButton,
                backButton
            ) = createRefs()

            Logo(
                id = R.drawable.logo_dark_blue,
                modifier = Modifier.constrainAs(logo){
                    top.linkTo(parent.top,96.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)}
            )

            Text(
                text = "Password reset code",
                style = TextStyle(
                    fontSize = 32.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.constrainAs(resetPasswordText){
                    top.linkTo(logo.bottom,96.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)}
            )

            Row(
                modifier = Modifier
                    .constrainAs(instructionsText){
                    top.linkTo(resetPasswordText.bottom)
                    start.linkTo(parent.start,16.dp)
                    end.linkTo(parent.end,16.dp)}
            ) {
                Text(
                    text = "We sent code to ",
                    style = TextStyle(
                        fontSize = 17.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(300),
                        color = Gray80,
                        textAlign = TextAlign.Center,
                    )
                )

                Text(
                    text = "asd55asd36@gmail.com",
                    style = TextStyle(
                        fontSize = 17.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(700),
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                    )
                )
            }

            ResetBoxes(
                digit1 = "",
                isError = false,
                onDigit1Change = {},
                digit2 = "",
                onDigit2Change = {},
                digit3 = "",
                onDigit3Change = {},
                digit4 = "",
                onDigit4Change = {},
                digit5 = "",
                onDigit5Change = {},
                digit6 = "",
                onDigit6Change = {},
                modifier = Modifier.constrainAs(boxes){
                    top.linkTo(instructionsText.bottom,32.dp)}
            )


            Text(
                text = "Incorrect or some field is empty",
                color = Color.Red,
                modifier = Modifier
                    .constrainAs(errorMessage) {
                        top.linkTo(boxes.bottom, 8.dp)
                        start.linkTo(parent.start, 16.dp)
                    }
                    .alpha(if (false) 1f else 0f)
            )

            StandardButton(
                buttonColor = DarkBlue,
                onClick = {},
                modifier = Modifier.constrainAs(continueButton){
                    top.linkTo(boxes.bottom,48.dp)},
            ) {

                Text(
                    text = "Continue",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(600),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                )


//                if (isLoading){
//                    CircularProgressIndicator(
//                        modifier = Modifier.size(32.dp),
//                        color = Color.White,
//                        strokeWidth = 5.dp
//                    )
//
//                } else {
//
//                    Text(
//                        text = "Reset",
//                        style = TextStyle(
//                            fontSize = 20.sp,
//                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
//                            fontWeight = FontWeight(600),
//                            color = Color.White,
//                            textAlign = TextAlign.Center,
//                        )
//                    )
//                }
            }

            ClickableText(
                text = AnnotatedString("<- Back to log in"),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(700),
                    color = Gray80,
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.constrainAs(backButton){
                    top.linkTo(continueButton.bottom,32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end) }
            ){
                //onClickBack()
            }
        }

//        if (message != ""){
//            LaunchedEffect(key1 = launchedEffectKey){
//                snackBarHostState.showSnackbar(message)
//            }
//        }
    }
}