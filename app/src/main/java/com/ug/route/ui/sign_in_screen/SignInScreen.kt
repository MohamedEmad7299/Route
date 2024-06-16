package com.ug.route.ui.sign_in_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.res.stringResource
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ug.route.R
import com.ug.route.networking.body_models.UserSignInBody
import com.ug.route.ui.design_matrials.text.Logo
import com.ug.route.ui.design_matrials.text.StandardButton
import com.ug.route.ui.design_matrials.text.TextWithPasswordTextField
import com.ug.route.ui.design_matrials.text.TextWithTextField
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.utils.Screen
import com.ug.route.utils.handelInternetError

@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel()
){
    val user by viewModel.user.collectAsState()
    val screenState by viewModel.screenState.collectAsState()

    SignInContent(
        user =  user,
        screenState = screenState,
        onPasswordChange = viewModel::onChangePassword,
        onEmailChange = viewModel::onChangeEmail,
        onChangePasswordVisibility = viewModel::onChangeVisibility,
        onClickVisibilityIcon = viewModel::updatePasswordVisibility,
        onClickLogin = {
            viewModel.signIn(navController)
        },
        navigateToSignUp = {
            viewModel.makeMessageEmpty()
            navController.navigate(Screen.SignUpScreen.route)
        },
        onClickForgotPassword = {
            viewModel.makeMessageEmpty()
            navController.navigate(Screen.ForgetPasswordScreen.route)
        },
        onInternetError = viewModel::onInternetError
    )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignInContent(
    user: UserSignInBody,
    screenState: SignInState,
    onPasswordChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onChangePasswordVisibility: (Boolean) -> Int,
    onClickVisibilityIcon: () -> Unit,
    onClickLogin: () -> Unit,
    navigateToSignUp : () -> Unit,
    onClickForgotPassword : () -> Unit,
    onInternetError : () -> Unit
){

    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    val systemUiController = rememberSystemUiController()


    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue),
        ) {

            SideEffect {
                systemUiController.setStatusBarColor(DarkBlue,darkIcons = false)
            }

            val (
                logo,
                welcomeMessage,
                email,
                password,
                forgetPasswordText,
                loginButton,
                createAccountText) = createRefs()

            Logo(
                id = R.drawable.logo_white,
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
                isError = screenState.isEmailError,
                text = stringResource(R.string.e_mail),
                modifier = Modifier.constrainAs(email) {
                    top.linkTo(welcomeMessage.bottom, 48.dp)
                },
                hint = stringResource(R.string.email_hint),
                value = user.email,
                onValueChange = onEmailChange,
                errorMessage = stringResource(R.string.email_error_message)
            )

            TextWithPasswordTextField(
                isError = screenState.isPasswordError,
                text = stringResource(R.string.password),
                hint = stringResource(R.string.password_hint),
                value =  user.password,
                modifier = Modifier.constrainAs(password) {
                    top.linkTo(email.bottom, 8.dp)
                },
                onValueChange = onPasswordChange,
                passwordVisibility = screenState.passwordVisibility,
                updatePasswordVisibility = onClickVisibilityIcon,
                onChangeVisibility = onChangePasswordVisibility,
                errorMessage = stringResource(R.string.password_error_message)
            )

            ClickableText(
                text = AnnotatedString(stringResource(R.string.forgot_password)),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(300),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .constrainAs(forgetPasswordText){
                        top.linkTo(password.bottom)
                        end.linkTo(parent.end,16.dp)
                    }
            ){ handelInternetError(context,onClickForgotPassword,onInternetError) }



            StandardButton(
                onClick = { handelInternetError(context,onClickLogin,onInternetError) },
                modifier = Modifier
                    .constrainAs(loginButton) {
                        top.linkTo(forgetPasswordText.bottom, 48.dp)
                    }
            ) {
                if (screenState.isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier.size(32.dp),
                        color = DarkBlue,
                        strokeWidth = 5.dp
                    )

                } else {

                    Text(
                        text = stringResource(R.string.login),
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

            Row(
                modifier = Modifier
                    .constrainAs(createAccountText) {
                        top.linkTo(loginButton.bottom, 32.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ){


                Text(
                    text = stringResource(R.string.don_t_have_an_account),
                    style = TextStyle(
                        fontSize = 17.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(300),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )

                ClickableText(
                    text = AnnotatedString(stringResource(R.string.create_one)),
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline
                    ),
                    onClick = { handelInternetError(context,navigateToSignUp,onInternetError) },
                )
            }

            if (screenState.message.isNotEmpty()){
                LaunchedEffect(key1 = screenState.launchedEffectKey){
                    snackBarHostState.showSnackbar(screenState.message)
                }
            }
        }
    }
}

