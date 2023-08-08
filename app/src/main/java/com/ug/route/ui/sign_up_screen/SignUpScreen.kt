package com.ug.route.ui.sign_up_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.ug.route.R
import com.ug.route.ui.design_matrials.text.Logo
import com.ug.route.ui.design_matrials.text.PasswordTextField
import com.ug.route.ui.design_matrials.text.StandardButton
import com.ug.route.ui.design_matrials.text.TextWithPasswordTextField
import com.ug.route.ui.design_matrials.text.TextWithTextField
import com.ug.route.ui.theme.DarkBlue


@Composable
fun SignUpScreen(navController: NavController){

    SignUpContent()
}

@Preview(showSystemUi = true)
@Composable
fun SignUpContent() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
    ){
        item {

            ConstraintLayout {

                val (
                    logo,
                    fullNameText,
                    fullNameTextField,
                    mobileNumberText,
                    mobileNumberTextField,
                    emailText,
                    emailTextField,
                    passwordText,
                    passwordTextField,
                    confirmPasswordTextField,
                    signUpButton
                ) = createRefs()

                Logo(
                    Modifier.constrainAs(logo) {
                        top.linkTo(parent.top, 48.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )


                TextWithTextField(
                    text = "Full Name",
                    textModifier = Modifier.constrainAs(fullNameText) {
                        top.linkTo(logo.bottom, 48.dp)
                        start.linkTo(parent.start,16.dp)
                    },
                    hint = "enter your full name",
                    value = "",
                    onValueChange = {  },
                    textFieldModifier = Modifier.constrainAs(fullNameTextField) {
                        top.linkTo(fullNameText.bottom, 16.dp)
                        start.linkTo(parent.start)
                    }
                )

                TextWithTextField(
                    text = "Mobile Number",
                    textModifier = Modifier.constrainAs(mobileNumberText) {
                        top.linkTo(fullNameTextField.bottom, 32.dp)
                        start.linkTo(parent.start,16.dp)
                    },
                    hint = "enter your mobile number",
                    value = "",
                    onValueChange = {  },
                    textFieldModifier = Modifier.constrainAs(mobileNumberTextField) {
                        top.linkTo(mobileNumberText.bottom, 16.dp)
                        start.linkTo(parent.start)
                    }
                )

                TextWithTextField(
                    text = "E-mail address",
                    textModifier = Modifier.constrainAs(emailText) {
                        top.linkTo(mobileNumberTextField.bottom, 32.dp)
                        start.linkTo(parent.start,16.dp)
                    },
                    hint = "enter your email address",
                    value = "",
                    onValueChange = {  },
                    textFieldModifier = Modifier.constrainAs(emailTextField) {
                        top.linkTo(emailText.bottom, 16.dp)
                        start.linkTo(parent.start)
                    }
                )

                TextWithPasswordTextField(
                    text = "Password",
                    textModifier = Modifier.constrainAs(passwordText) {
                        top.linkTo(emailTextField.bottom, 32.dp)
                        start.linkTo(parent.start,16.dp)
                    },
                    hint = "enter your password",
                    value = "",
                    onValueChange = {},
                    passwordVisibility = false,
                    onClickVisibilityIcon = {},
                    onChangePasswordVisibility = { R.drawable.visibility_off },
                    textFieldModifier = Modifier.constrainAs(passwordTextField) {
                        top.linkTo(passwordText.bottom, 16.dp)
                        start.linkTo(parent.start)
                    }
                )

                PasswordTextField(
                    hint = "repeat your password",
                    value = "",
                    onValueChange = {},
                    passwordVisibility = false,
                    onClickVisibilityIcon = {},
                    onChangePasswordVisibility = { R.drawable.visibility_off },
                    modifier = Modifier.constrainAs(confirmPasswordTextField) {
                        top.linkTo(passwordTextField.bottom, 16.dp)
                        start.linkTo(parent.start)
                    }
                )

                StandardButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(bottom = 48.dp)
                        .constrainAs(signUpButton) {
                            top.linkTo(confirmPasswordTextField.bottom, 48.dp)
                        }
                ) {
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
        }
    }
}
