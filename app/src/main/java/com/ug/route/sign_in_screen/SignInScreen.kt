package com.ug.route.sign_in_screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.ug.route.R
import com.ug.route.design_matrials.text.Text18
import com.ug.route.ui.theme.DarkBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavController){

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

        Image(

            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = 64.dp)
                .constrainAs(logo) {

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

        Text18(
            text = "User Name",
            modifier = Modifier.constrainAs(usernameText) {
                top.linkTo(welcomeMessage.bottom, 48.dp)
                start.linkTo(parent.start, 16.dp)
            }
        )

        OutlinedTextField(
            placeholder = {
                Text(
                    text = "enter your username",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(300),
                        color = Color(0xB2000000),
                        textAlign = TextAlign.Center,
                    )
                )},
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .constrainAs(userTextField) {
                    top.linkTo(usernameText.bottom, 16.dp)
                    start.linkTo(parent.start)
                },
            shape = RoundedCornerShape(16.dp),
            value = "",
            onValueChange = {},
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
            ),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(300)
            )
        )

        Text18(
            text = "Password",
            modifier = Modifier.constrainAs(passwordText) {
                top.linkTo(userTextField.bottom, 32.dp)
                start.linkTo(parent.start , 16.dp)
            }
        )

        OutlinedTextField(
            placeholder = {
                Text(
                    text = "enter your password",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(300),
                        color = Color(0xB2000000),
                        textAlign = TextAlign.Center,
                    )
                )},
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .constrainAs(passwordTextField) {
                    top.linkTo(passwordText.bottom, 16.dp)
                    start.linkTo(parent.start)
                },
            shape = RoundedCornerShape(16.dp),
            value = "",
            onValueChange = {},
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.visibility_off),
                        contentDescription = "")
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
            ),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(300)
            )
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

        Button(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .constrainAs(loginButton) {
                    top.linkTo(forgetPasswordText.bottom, 48.dp)
                },
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
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

        Text18(
            text = "Don’t have an account? Create One",
            modifier = Modifier.constrainAs(createAccountText){
                top.linkTo(loginButton.bottom,32.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}