package com.ug.route.ui.forget_password_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ug.route.R
import com.ug.route.ui.design_matrials.text.BackToLogin
import com.ug.route.ui.design_matrials.text.Logo
import com.ug.route.ui.design_matrials.text.StandardButton
import com.ug.route.ui.design_matrials.text.TextWithTextField
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.ui.theme.Gray80
import com.ug.route.utils.Screen
import com.ug.route.utils.handelInternetError
@Composable
fun ForgetPasswordScreen(
    navController : NavController,
    viewModel: ForgetPasswordViewModel = hiltViewModel()
){

    val email by viewModel.email.collectAsState()
    val screenState by viewModel.screenState.collectAsState()

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(Color.White,darkIcons = true)
    }

    ForgetPasswordContent(
        email = email,
        screenState = screenState,
        onClickReset = {
            viewModel.resetPassword(navController)
        },
        onClickBack = { navController.navigate(Screen.SignInScreen.route){
            popUpTo(navController.graph.id){
                inclusive = true
            }
        } },
        onChangeEmail = viewModel::onChangeEmail,
        onInternetError = viewModel::onInternetError,
    )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ForgetPasswordContent(
    email : String,
    screenState: ForgetPasswordState,
    onChangeEmail : (String) -> Unit,
    onClickReset : () -> Unit,
    onClickBack : () -> Unit,
    onInternetError : () -> Unit
){

    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

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


            TextWithTextField(
                text = "Email",
                textColor = Color(0xFF000000),
                textModifier = Modifier.constrainAs(emailText){
                    top.linkTo(instructionsText.bottom,32.dp)
                    start.linkTo(parent.start,16.dp)},
                isError = screenState.isEmailError,
                hint = "enter your email",
                value = email,
                onValueChange = onChangeEmail,
                textFieldModifier = Modifier.constrainAs(emailTextField){
                    top.linkTo(emailText.bottom,16.dp)},
                errorMessage = "Incorrect or field is empty",
                errorModifier = Modifier
                    .constrainAs(errorMessage) {
                        top.linkTo(emailTextField.bottom, 8.dp)
                        start.linkTo(parent.start, 16.dp)
                    },
                shape = RoundedCornerShape(8.dp)
            )

            StandardButton(
                buttonColor = DarkBlue,
                onClick = { handelInternetError(context,onClickReset,onInternetError) },
                modifier = Modifier.constrainAs(resetButton){
                    top.linkTo(emailTextField.bottom,48.dp)},
            ) {

                if (screenState.isLoading){
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

            BackToLogin(
                modifier = Modifier.constrainAs(backButton){
                    top.linkTo(resetButton.bottom,32.dp)
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

