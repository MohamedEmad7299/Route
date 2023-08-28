package com.ug.route.ui.code_validation_screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ug.route.R
import com.ug.route.ui.design_matrials.text.BackToLogin
import com.ug.route.ui.design_matrials.text.CodeTextField
import com.ug.route.ui.design_matrials.text.Logo
import com.ug.route.ui.design_matrials.text.StandardButton
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.ui.theme.Gray80
import com.ug.route.utils.Screen
import com.ug.route.utils.handelInternetError
@Composable
fun CodeValidationScreen(
    navController: NavController,
    viewModel: CodeValidationViewModel = hiltViewModel()
){

    val screenState by viewModel.screenState.collectAsState()

    CodeValidationContent(
        screenState = screenState,
        onChangeCode = viewModel::onChangeCode,
        onClickBack = { navController.navigate(Screen.SignInScreen.route){
            popUpTo(navController.graph.id){
                inclusive = true
            }
        } },
        onClickHere = viewModel::resetPassword,
        updateMessageAndKeyOnClick = viewModel::updateMessageAndKeyOnClick,
        disableClickHere = viewModel::disableClickHere,
        codeValidation = viewModel::codeValidation,
        onInternetError = viewModel::onInternetError
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CodeValidationContent(
    screenState: CodeValidationState,
    onChangeCode : (String) -> Unit,
    onClickBack : () -> Unit,
    onClickHere : () -> Unit,
    updateMessageAndKeyOnClick : (Boolean) -> Unit,
    disableClickHere : () -> Unit,
    codeValidation : () -> Unit,
    onInternetError : () -> Unit
){

    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) {

        BackHandler(
            onBack = onClickBack
        )

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            val (
                logo,
                resetPasswordText,
                instructionsText,
                code,
                errorMessage,
                continueButton,
                backButton,
                reSendCode,
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
                    text = screenState.email,
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

            CodeTextField(
                isError = screenState.isError,
                value = screenState.resetCode,
                onValueChange = onChangeCode,
                modifier =  Modifier.constrainAs(code){
                    top.linkTo(instructionsText.bottom,24.dp)}
            )

            Text(
                text = "Field is empty",
                color = Color.Red,
                modifier = Modifier
                    .constrainAs(errorMessage) {
                        top.linkTo(code.bottom, 8.dp)
                        start.linkTo(parent.start, 16.dp)
                    }
                    .alpha(if (screenState.isError) 1f else 0f)
            )

            StandardButton(
                buttonColor = DarkBlue,
                onClick = {
                    keyboardController?.hide()
                    handelInternetError(context,codeValidation,onInternetError) },
                modifier = Modifier.constrainAs(continueButton){
                    top.linkTo(code.bottom,48.dp)},
            ) {

                if (screenState.isLoading){

                    CircularProgressIndicator(
                        modifier = Modifier.size(32.dp),
                        color = Color.White,
                        strokeWidth = 5.dp
                    )

                } else {

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
                }
            }


            Row(
                modifier = Modifier
                    .constrainAs(reSendCode){
                        top.linkTo(continueButton.bottom,32.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end) }
            ) {
                Text(
                    text = "Don't receive the code? ",
                    style = TextStyle(
                        fontSize = 17.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(300),
                        color = Gray80,
                        textAlign = TextAlign.Center,
                    )
                )

                ClickableText(
                    text = AnnotatedString("Click here"),
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(700),
                        color = DarkBlue,
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline
                    )
                ){
                    handelInternetError(context,{
                        if (screenState.isClickable) onClickHere()
                        updateMessageAndKeyOnClick(screenState.isClickable)
                        if (screenState.isClickable) disableClickHere()
                    },onInternetError)
                }
            }

            BackToLogin(
                modifier = Modifier.constrainAs(backButton){
                    top.linkTo(reSendCode.bottom,32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end) },
                onClickBack = onClickBack
            )

        }

        if (screenState.message != ""){
            LaunchedEffect(key1 = screenState.launchedEffectKey){
                snackBarHostState.showSnackbar(screenState.message)
            }
        }
    }
}