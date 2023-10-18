package com.ug.route.ui.reset_password_screen

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.ug.route.networking.body_models.ResetPasswordBody
import com.ug.route.ui.design_matrials.text.BackToLogin
import com.ug.route.ui.design_matrials.text.Logo
import com.ug.route.ui.design_matrials.text.StandardButton
import com.ug.route.ui.design_matrials.text.TextWithPasswordTextField
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.ui.theme.Gray80
import com.ug.route.utils.Screen
import com.ug.route.utils.handelInternetError

@Composable
fun ResetPasswordScreen(
    navController : NavController,
    viewModel: ResetPasswordViewModel = hiltViewModel()
){

    val screenState by viewModel.screenState.collectAsState()
    val rePassword by viewModel.rePassword.collectAsState()
    val resetPasswordDTO by viewModel.resetPasswordDto.collectAsState()

    ResetPasswordContent(
        screenState = screenState,
        onInternetError = viewModel::onInternetError,
        rePassword = rePassword,
        resetPassword = {viewModel.resetPassword(navController)},
        onChangeRePassword = viewModel::onChangeRePassword,
        onChangePassword = viewModel::onChangePassword,
        onClickBack = {
            navController.navigate(Screen.SignInScreen.route){
                popUpTo(navController.graph.id){
                    inclusive = true
                }
            }
        },
        resetPasswordBody = resetPasswordDTO,
        updatePasswordVisibility = viewModel::updatePasswordVisibility,
        updateRePasswordVisibility = viewModel::updateRePasswordVisibility,
        onChangeVisibility = viewModel::onChangeVisibility
    )
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ResetPasswordContent(
    screenState : ResetPasswordState,
    resetPasswordBody: ResetPasswordBody,
    onChangePassword : (String) -> Unit,
    onChangeRePassword : (String) -> Unit,
    updatePasswordVisibility : () -> Unit,
    updateRePasswordVisibility: () -> Unit,
    onChangeVisibility: (Boolean) -> Int,
    rePassword : String,
    resetPassword : () -> Unit,
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
                setNewPasswordText,
                instructionsText,
                password,
                repassword,
                resetButton,
                backButton
            ) = createRefs()

            Logo(
                id = R.drawable.logo_dark_blue,
                modifier = Modifier.constrainAs(logo){
                    top.linkTo(parent.top,56.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)}
            )

            Text(
                text = stringResource(R.string.set_new_password),
                style = TextStyle(
                    fontSize = 32.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.constrainAs(setNewPasswordText){
                    top.linkTo(logo.bottom,56.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)}
            )

            Text(
                text = stringResource(R.string.must_be_at_least_6_characters),
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(300),
                    color = Gray80,
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.constrainAs(instructionsText){
                    top.linkTo(setNewPasswordText.bottom)
                    start.linkTo(parent.start,16.dp)
                    end.linkTo(parent.end,16.dp)}
            )

            TextWithPasswordTextField(
                textColor = Color(0xFF000000),
                isError = screenState.isPasswordError,
                text = stringResource(id = R.string.password),
                modifier = Modifier.constrainAs(password) {
                    top.linkTo(instructionsText.bottom, 32.dp)
                },
                hint = stringResource(id = R.string.password_hint),
                value = resetPasswordBody.newPassword,
                onValueChange = onChangePassword,
                passwordVisibility = screenState.passwordVisibility,
                updatePasswordVisibility = updatePasswordVisibility,
                onChangeVisibility = onChangeVisibility,
                errorMessage = stringResource(id = R.string.password_error_message),
                shape = RoundedCornerShape(8.dp)
            )

            TextWithPasswordTextField(
                textColor = Color(0xFF000000),
                isError = screenState.isRePasswordError,
                text = stringResource(id = R.string.confirm_password),
                modifier = Modifier.constrainAs(repassword) {
                    top.linkTo(password.bottom, 8.dp)
                },
                hint = stringResource(id = R.string.confirm_password_hint),
                value = rePassword,
                onValueChange = onChangeRePassword,
                passwordVisibility = screenState.rePasswordVisibility,
                updatePasswordVisibility = updateRePasswordVisibility,
                onChangeVisibility = onChangeVisibility,
                errorMessage = stringResource(id = R.string.confirm_password_error_message),
                shape = RoundedCornerShape(8.dp)
            )

            StandardButton(
                buttonColor = DarkBlue,
                onClick = { handelInternetError(context,resetPassword,onInternetError) },
                modifier = Modifier.constrainAs(resetButton){
                    top.linkTo(repassword.bottom,32.dp)},
            ) {

                if (screenState.isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier.size(32.dp),
                        color = Color.White,
                        strokeWidth = 5.dp
                    )

                } else {

                    Text(
                        text = stringResource(R.string.reset),
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

        if (screenState.message.isNotEmpty()){
            LaunchedEffect(key1 = screenState.launchedEffectKey){
                snackBarHostState.showSnackbar(screenState.message)
            }
        }
    }
}

