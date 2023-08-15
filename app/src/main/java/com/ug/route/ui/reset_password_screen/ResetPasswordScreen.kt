package com.ug.route.ui.reset_password_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ug.route.R
import com.ug.route.ui.design_matrials.text.Logo
import com.ug.route.ui.design_matrials.text.StandardButton
import com.ug.route.ui.design_matrials.text.StandardTextField
import com.ug.route.ui.design_matrials.text.Text18
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.ui.theme.Gray80


@Composable
fun ForgotPasswordScreen(
    navController : NavController,
    viewModel: ResetPasswordViewModel = hiltViewModel()
){

    val email by viewModel.email.collectAsState()
    val isEmailError by viewModel.isEmailError.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val message by viewModel.message.collectAsState()
    val launchedEffectKey by viewModel.launchedEffectKey.collectAsState()

    ForgotPasswordContent(
        email = email,
        isEmailError = isEmailError,
        isLoading = isLoading,
        message = message,
        launchedEffectKey = launchedEffectKey,
        onClickReset = viewModel::resetPassword,
        onClickBack = { navController.navigate("signIn_screen") },
        onChangeEmail = viewModel::onChangeEmail
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ForgotPasswordContent(
    email : String,
    onChangeEmail : (String) -> Unit,
    onClickReset : () -> Unit,
    message : String,
    launchedEffectKey : Boolean,
    onClickBack : () -> Unit,
    isLoading : Boolean,
    isEmailError : Boolean
){

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
                forgetPasswordText,
                instructionsText,
                emailText,
                emailTextField,
                errorMessage,
                resetButton,
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
                text = "Forgot Password?",
                style = TextStyle(
                    fontSize = 32.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.constrainAs(forgetPasswordText){
                    top.linkTo(logo.bottom,96.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)}
            )

            Text(
                text = "No worries, we’ll send you reset instructions",
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(300),
                    color = Gray80,
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.constrainAs(instructionsText){
                    top.linkTo(forgetPasswordText.bottom)
                    start.linkTo(parent.start,16.dp)
                    end.linkTo(parent.end,16.dp)}
            )

            Text18(
                text = "Email",
                color = Color(0xFF000000),
                modifier = Modifier.constrainAs(emailText){
                    top.linkTo(instructionsText.bottom,32.dp)
                    start.linkTo(parent.start,16.dp)}
            )

            StandardTextField(
                isError = isEmailError,
                hint = "enter your email",
                value = email,
                onValueChange = onChangeEmail,
                modifier = Modifier.constrainAs(emailTextField){
                    top.linkTo(emailText.bottom,16.dp)},
                shape = RoundedCornerShape(8.dp),
            )

            Text(
                text = "Incorrect or field is empty",
                color = Color.Red,
                modifier = Modifier
                    .constrainAs(errorMessage){
                        top.linkTo(emailTextField.bottom, 8.dp)
                        start.linkTo(parent.start,16.dp)
                    }
                    .alpha(if (isEmailError) 1f else 0f)
            )

            StandardButton(
                buttonColor = DarkBlue,
                onClick = onClickReset,
                modifier = Modifier.constrainAs(resetButton){
                    top.linkTo(emailTextField.bottom,48.dp)},
            ) {

                if (isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier.size(32.dp),
                        color = Color.White,
                        strokeWidth = 5.dp
                    )

                } else {

                    Text(
                        text = "Reset",
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
                    top.linkTo(resetButton.bottom,32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end) }
            ){
                onClickBack()
            }
        }

        if (message != ""){
            LaunchedEffect(key1 = launchedEffectKey){
                snackBarHostState.showSnackbar(message)
            }
        }
    }
}

