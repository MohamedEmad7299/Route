package com.ug.route.ui.sign_up_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import com.ug.route.networking.dto_models.UserSignUpDTO
import com.ug.route.ui.design_matrials.text.Logo
import com.ug.route.ui.design_matrials.text.StandardButton
import com.ug.route.ui.design_matrials.text.TextWithPasswordTextField
import com.ug.route.ui.design_matrials.text.TextWithTextField
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.utils.handelInternetError

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
){
    val user by viewModel.user.collectAsState()
    val screenState by viewModel.screenState.collectAsState()

    SignUpContent(
        user = user,
        screenState = screenState,
        onChangeName = viewModel::onChangeName,
        onChangeEmail = viewModel::onChangeEmail,
        onChangePassword = viewModel::onChangePassword,
        onChangePhone = viewModel::onChangePhone,
        signUp = {
            viewModel.signUp(navController)
        },
        onClickPasswordVisibilityIcon = viewModel::updatePasswordVisibility,
        onChangePasswordVisibility = viewModel::onChangeVisibility,
        onClickRePasswordVisibilityIcon = viewModel::updateRePasswordVisibility,
        onChangeRePassword = viewModel::onChangeRePassword,
        onInternetError = viewModel::onInternetError
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpContent(
    user : UserSignUpDTO,
    screenState: SignUpState,
    onChangeName : (String) -> Unit,
    onChangePhone : (String) -> Unit,
    onChangeEmail : (String) -> Unit,
    onChangePassword : (String) -> Unit,
    onChangeRePassword : (String) -> Unit,
    signUp : () -> Unit,
    onClickPasswordVisibilityIcon :  () -> Unit,
    onChangePasswordVisibility :  (Boolean) -> Int,
    onClickRePasswordVisibilityIcon :  () -> Unit,
    onInternetError : () -> Unit
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue)
        ){
            item {

                ConstraintLayout {

                    val logo = createRef()
                    val fullName = createRef()
                    val mobileNumber = createRef()
                    val email = createRef()
                    val password = createRef()
                    val rePassword = createRef()
                    val signUpButton = createRef()

                    Logo(
                        id = R.drawable.logo_white,
                        Modifier.constrainAs(logo) {
                            top.linkTo(parent.top, 48.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    )


                    TextWithTextField(
                        isError = screenState.isNameError,
                        text = stringResource(R.string.full_name),
                        modifier = Modifier.constrainAs(fullName) {
                            top.linkTo(logo.bottom, 48.dp)
                        },
                        hint = stringResource(R.string.name_hint),
                        value = user.name,
                        onValueChange = onChangeName,
                        errorMessage = stringResource(R.string.name_error_message))


                    TextWithTextField(
                        isError = screenState.isPhoneError,
                        text = stringResource(R.string.mobile_number),
                        modifier = Modifier.constrainAs(mobileNumber) {
                            top.linkTo(fullName.bottom, 8.dp)
                        },
                        hint = stringResource(R.string.mobile_number_hint),
                        value = user.phone,
                        onValueChange = onChangePhone,
                        errorMessage = stringResource(R.string.mobile_number_error_message)
                    )

                    TextWithTextField(
                        isError = screenState.isEmailError,
                        text = stringResource(R.string.e_mail),
                        modifier = Modifier.constrainAs(email) {
                            top.linkTo(mobileNumber.bottom, 8.dp)
                        },
                        hint = stringResource(R.string.email_hint),
                        value = user.email,
                        onValueChange = onChangeEmail,
                        errorMessage = stringResource(R.string.email_error_message)
                    )

                    TextWithPasswordTextField(
                        isError = screenState.isPasswordError,
                        text = stringResource(R.string.password),
                        modifier = Modifier.constrainAs(password) {
                            top.linkTo(email.bottom, 8.dp)
                        },
                        hint = stringResource(R.string.password_hint),
                        value = user.password,
                        onValueChange = onChangePassword,
                        passwordVisibility = screenState.passwordVisibility,
                        updatePasswordVisibility = onClickPasswordVisibilityIcon,
                        onChangeVisibility = onChangePasswordVisibility,
                        errorMessage = stringResource(R.string.password_error_message)
                    )

                    TextWithPasswordTextField(
                        isError = screenState.isRePasswordError,
                        text = stringResource(R.string.confirm_password),
                        modifier = Modifier.constrainAs(rePassword) {
                            top.linkTo(password.bottom, 8.dp)
                        },
                        hint = stringResource(R.string.confirm_password_hint),
                        value = user.rePassword,
                        onValueChange = onChangeRePassword,
                        passwordVisibility = screenState.rePasswordVisibility,
                        updatePasswordVisibility = onClickRePasswordVisibilityIcon,
                        onChangeVisibility = onChangePasswordVisibility,
                        errorMessage = stringResource(R.string.confirm_password_error_message)
                    )

                    StandardButton(
                        onClick = { handelInternetError(context,signUp,onInternetError) },
                        modifier = Modifier
                            .padding(bottom = 48.dp)
                            .constrainAs(signUpButton) {
                                top.linkTo(rePassword.bottom, 48.dp)
                            }
                    ){

                        if (screenState.isLoading){

                            CircularProgressIndicator(
                                modifier = Modifier.size(32.dp),
                                color = DarkBlue,
                                strokeWidth = 5.dp
                            )

                        } else {

                            Text(
                                text = stringResource(R.string.sign_up),
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

                    if (screenState.message.isNotEmpty()){
                        LaunchedEffect(key1 = screenState.launchedEffectKey){
                            snackBarHostState.showSnackbar(screenState.message)
                        }
                    }
                }
            }
        }
    }
}
