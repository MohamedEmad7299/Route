package com.ug.route.ui.sign_up_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import kotlinx.coroutines.flow.update

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
){
    val user by viewModel.user.collectAsState()
    val passwordVisibility by viewModel.passwordVisibility.collectAsState()
    val rePasswordVisibility by viewModel.rePasswordVisibility.collectAsState()

    val isPasswordError by viewModel.isPasswordError.collectAsState()
    val isEmailError by viewModel.isEmailError.collectAsState()
    val isRePasswordError by viewModel.isRePasswordError.collectAsState()
    val isNameError by viewModel.isNameError.collectAsState()
    val isPhoneError by viewModel.isPhoneError.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val message by viewModel.message.collectAsState()
    val launchedEffectKey by viewModel.launchedEffectKey.collectAsState()

    SignUpContent(
        user = user,
        onChangeName = viewModel::onChangeName,
        onChangeEmail = viewModel::onChangeEmail,
        onChangePassword = viewModel::onChangePassword,
        onChangePhone = viewModel::onChangePhone,
        isPasswordError = isPasswordError,
        isEmailError = isEmailError,
        isNameError = isNameError,
        isPhoneError = isPhoneError,
        signUp = viewModel::signUp,
        onClickPasswordVisibilityIcon = {
            viewModel.passwordVisibility.update { !viewModel.passwordVisibility.value }
        },
        passwordVisibility = passwordVisibility,
        onChangePasswordVisibility = viewModel::onChangeVisibility,
        isLoading = isLoading,
        message = message,
        launchedEffectKey = launchedEffectKey,
        rePasswordVisibility = rePasswordVisibility,
        onClickRePasswordVisibilityIcon = {
            viewModel.rePasswordVisibility.update { !viewModel.rePasswordVisibility.value }
        },
        isRePasswordError = isRePasswordError,
        onChangeRePassword = viewModel::onChangeRePassword
    )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpContent(
    user : UserSignUpDTO,
    onChangeName : (String) -> Unit,
    onChangePhone : (String) -> Unit,
    onChangeEmail : (String) -> Unit,
    onChangePassword : (String) -> Unit,
    onChangeRePassword : (String) -> Unit,
    isNameError : Boolean,
    isPhoneError : Boolean,
    isEmailError : Boolean,
    isPasswordError : Boolean,
    signUp : () -> Unit,
    onClickPasswordVisibilityIcon :  () -> Unit,
    passwordVisibility : Boolean,
    rePasswordVisibility : Boolean,
    onChangePasswordVisibility :  (Boolean) -> Int,
    onClickRePasswordVisibilityIcon :  () -> Unit,
    isLoading : Boolean,
    message : String,
    launchedEffectKey : Boolean,
    isRePasswordError : Boolean
) {

    val snackBarHostState = remember { SnackbarHostState() }

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
                    val fullNameText = createRef()
                    val mobileNumberText = createRef()
                    val emailText = createRef()
                    val passwordText = createRef()
                    val rePasswordText = createRef()
                    val signUpButton = createRef()

                    val fullNameTextField = createRef()
                    val mobileNumberTextField = createRef()
                    val emailTextField = createRef()
                    val passwordTextField = createRef()
                    val rePasswordTextField = createRef()

                    val nameError = createRef()
                    val phoneError = createRef()
                    val emailError = createRef()
                    val passwordError = createRef()
                    val rePasswordError = createRef()

                    Logo(
                        id = R.drawable.logo_white,
                        Modifier.constrainAs(logo) {
                            top.linkTo(parent.top, 48.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    )


                    TextWithTextField(
                        isError = isNameError,
                        text = "Full Name",
                        textModifier = Modifier.constrainAs(fullNameText) {
                            top.linkTo(logo.bottom, 48.dp)
                            start.linkTo(parent.start,16.dp)
                        },
                        hint = "enter your full name",
                        value = user.name,
                        onValueChange = onChangeName,
                        textFieldModifier = Modifier.constrainAs(fullNameTextField) {
                            top.linkTo(fullNameText.bottom, 16.dp)
                            start.linkTo(parent.start)
                        },
                        errorMessage = "field is empty",
                        errorVisibility = isNameError,
                        errorModifier = Modifier.constrainAs(nameError){
                            top.linkTo(fullNameTextField.bottom, 8.dp)
                            start.linkTo(parent.start,16.dp)
                        }
                    )

                    TextWithTextField(
                        isError = isPhoneError,
                        text = "Mobile Number",
                        textModifier = Modifier.constrainAs(mobileNumberText) {
                            top.linkTo(fullNameTextField.bottom, 32.dp)
                            start.linkTo(parent.start,16.dp)
                        },
                        hint = "enter your mobile number",
                        value = user.phone,
                        onValueChange = onChangePhone,
                        textFieldModifier = Modifier.constrainAs(mobileNumberTextField) {
                            top.linkTo(mobileNumberText.bottom, 16.dp)
                            start.linkTo(parent.start)
                        },
                        errorMessage = "field is empty",
                        errorVisibility = isPhoneError,
                        errorModifier = Modifier.constrainAs(phoneError){
                            top.linkTo(mobileNumberTextField.bottom, 8.dp)
                            start.linkTo(parent.start,16.dp)
                        }
                    )

                    TextWithTextField(
                        isError = isEmailError,
                        text = "E-mail address",
                        textModifier = Modifier.constrainAs(emailText) {
                            top.linkTo(mobileNumberTextField.bottom, 32.dp)
                            start.linkTo(parent.start,16.dp)
                        },
                        hint = "enter your email address",
                        value = user.email,
                        onValueChange = onChangeEmail,
                        textFieldModifier = Modifier.constrainAs(emailTextField) {
                            top.linkTo(emailText.bottom, 16.dp)
                            start.linkTo(parent.start)
                        },
                        errorMessage = "incorrect or field is empty",
                        errorVisibility = isEmailError,
                        errorModifier = Modifier.constrainAs(emailError){
                            top.linkTo(emailTextField.bottom, 8.dp)
                            start.linkTo(parent.start,16.dp)
                        }
                    )

                    TextWithPasswordTextField(
                        isError = isPasswordError,
                        text = "Password",
                        textModifier = Modifier.constrainAs(passwordText) {
                            top.linkTo(emailTextField.bottom, 32.dp)
                            start.linkTo(parent.start,16.dp)
                        },
                        hint = "enter your password",
                        value = user.password,
                        onValueChange = onChangePassword,
                        passwordVisibility = passwordVisibility,
                        onClickVisibilityIcon = onClickPasswordVisibilityIcon,
                        onChangeVisibility = onChangePasswordVisibility,
                        textFieldModifier = Modifier.constrainAs(passwordTextField) {
                            top.linkTo(passwordText.bottom, 16.dp)
                            start.linkTo(parent.start)
                        },
                        errorMessage = "field is empty",
                        errorVisibility = isPasswordError,
                        errorModifier = Modifier.constrainAs(passwordError){
                            top.linkTo(passwordTextField.bottom, 8.dp)
                            start.linkTo(parent.start,16.dp)
                        }
                    )

                    TextWithPasswordTextField(
                        isError = isRePasswordError,
                        text = "Confirm Password",
                        textModifier = Modifier.constrainAs(rePasswordText) {
                            top.linkTo(passwordTextField.bottom, 32.dp)
                            start.linkTo(parent.start,16.dp)
                        },
                        hint = "repeat your password",
                        value = user.rePassword,
                        onValueChange = onChangeRePassword,
                        passwordVisibility = rePasswordVisibility,
                        onClickVisibilityIcon = onClickRePasswordVisibilityIcon,
                        onChangeVisibility = onChangePasswordVisibility,
                        textFieldModifier = Modifier.constrainAs(rePasswordTextField) {
                            top.linkTo(rePasswordText.bottom, 16.dp)
                            start.linkTo(parent.start)
                        },
                        errorMessage = "field is empty or not equal to the password",
                        errorVisibility = isRePasswordError,
                        errorModifier = Modifier.constrainAs(rePasswordError){
                            top.linkTo(rePasswordTextField.bottom, 8.dp)
                            start.linkTo(parent.start,16.dp)
                        }
                    )

                    StandardButton(
                        onClick = signUp,
                        modifier = Modifier
                            .padding(bottom = 48.dp)
                            .constrainAs(signUpButton) {
                                top.linkTo(rePasswordTextField.bottom, 48.dp)
                            }
                    ){

                        if (isLoading){
                            CircularProgressIndicator(
                                modifier = Modifier.size(32.dp),
                                color = DarkBlue,
                                strokeWidth = 5.dp
                            )

                        } else {

                            Text(
                                text = "Sign Up",
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

                    if (message != ""){
                        LaunchedEffect(key1 = launchedEffectKey){
                            snackBarHostState.showSnackbar(message)
                        }
                    }
                }
            }
        }
    }
}
