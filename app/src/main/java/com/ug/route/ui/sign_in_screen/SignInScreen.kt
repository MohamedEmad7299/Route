package com.ug.route.ui.sign_in_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
import com.ug.route.data.models.User
import com.ug.route.ui.design_matrials.text.Logo
import com.ug.route.ui.design_matrials.text.StandardButton
import com.ug.route.ui.design_matrials.text.Text18
import com.ug.route.ui.design_matrials.text.TextWithPasswordTextField
import com.ug.route.ui.design_matrials.text.TextWithTextField
import com.ug.route.ui.theme.DarkBlue

@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel()
){
    val user by viewModel.user.collectAsState()
    val passwordVisibility by viewModel.passwordVisibility.collectAsState()
    val message by viewModel.message.collectAsState()
    val launchedEffectKey by viewModel.launchedEffectKey.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    SignInContent(
        user =  user,
        passwordVisibility = passwordVisibility,
        onPasswordChange = viewModel::onChangePassword,
        onEmailChange = viewModel::onChangeEmail,
        onChangePasswordVisibility = viewModel::onChangePasswordVisibility,
        onClickVisibilityIcon = viewModel::onClickVisibilityIcon,
        onClickLogin = viewModel::signIn,
        message = message,
        launchedEffectKey = launchedEffectKey,
        isLoading = isLoading,
        createAccount = {
            navController.navigate("signUp_screen")
        }
    )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInContent(
    user: User,
    onPasswordChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onChangePasswordVisibility: (Boolean) -> Int,
    passwordVisibility: Boolean,
    onClickVisibilityIcon: () -> Unit,
    onClickLogin: () -> Unit,
    message: String,
    launchedEffectKey : Boolean,
    isLoading : Boolean,
    createAccount : () -> Unit
){

    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue),
        ) {

            val (
                logo,
                welcomeMessage,
                usernameText,
                userTextField,
                passwordTextField,
                forgetPasswordText,
                loginButton,
                createAccountText,
                passwordText) = createRefs()

            Logo(
                modifier = Modifier.constrainAs(logo) {

                    top.linkTo(parent.top, 48.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Text(
                text = stringResource(R.string.welcome_back_to_route),
                modifier = Modifier
                    .width(290.dp)
                    .constrainAs(welcomeMessage) {
                        top.linkTo(logo.bottom, 64.dp)
                        start.linkTo(parent.start, 16.dp)
                    },
                style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                )
            )

            TextWithTextField(
                text = "E-mail",
                textModifier = Modifier.constrainAs(usernameText) {
                    top.linkTo(welcomeMessage.bottom, 48.dp)
                    start.linkTo(parent.start, 16.dp)
                },
                hint = "enter your e-mail",
                value = user.email,
                onValueChange = onEmailChange,
                textFieldModifier = Modifier.constrainAs(userTextField) {
                    top.linkTo(usernameText.bottom, 16.dp)
                    start.linkTo(parent.start)
                }
            )

            TextWithPasswordTextField(
                text = "Password",
                hint = "enter your password",
                value =  user.password,
                textModifier = Modifier.constrainAs(passwordText) {
                    top.linkTo(userTextField.bottom, 32.dp)
                    start.linkTo(parent.start , 16.dp)
                },
                onValueChange = onPasswordChange,
                passwordVisibility = passwordVisibility,
                onClickVisibilityIcon = onClickVisibilityIcon,
                onChangePasswordVisibility = onChangePasswordVisibility,
                textFieldModifier = Modifier
                    .constrainAs(passwordTextField) {
                        top.linkTo(passwordText.bottom, 16.dp)
                        start.linkTo(parent.start)
                    }
            )

            Text(
                text = "Forget password?",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(300),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .constrainAs(forgetPasswordText){
                        top.linkTo(passwordTextField.bottom,16.dp)
                        end.linkTo(parent.end,16.dp)
                    }
            )


            StandardButton(
                onClick = onClickLogin,
                modifier = Modifier
                    .constrainAs(loginButton) {
                        top.linkTo(forgetPasswordText.bottom, 48.dp)
                    }
            ) {
                if (isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier.size(32.dp),
                        color = DarkBlue,
                        strokeWidth = 5.dp
                    )

                } else {

                    Text(
                        text = "Login",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(600),
                            color = DarkBlue,
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }

            Text18(
                text = "Don’t have an account? Create One",
                modifier = Modifier
                    .clickable { createAccount() }
                    .constrainAs(createAccountText){
                    top.linkTo(loginButton.bottom,32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            if (message != ""){
                LaunchedEffect(key1 = launchedEffectKey){
                    snackBarHostState.showSnackbar(message)
                }
            }
        }
    }
}

