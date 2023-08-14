package com.ug.route.ui.forgot_password_screen



import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ug.route.R
import com.ug.route.ui.design_matrials.text.Logo
import com.ug.route.ui.design_matrials.text.Text18
import com.ug.route.ui.theme.Gray80


@Composable
fun ForgotPasswordScreen(

){

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showSystemUi = true)
@Composable
fun ForgotPasswordContent(
){

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (
            logo,
            forgetPasswordText,
            instructionsText,
            emailText,
            emailTextField,
            resetButton
        ) = createRefs()

        Logo(
            id = R.drawable.logo_dark_blue,
            modifier = Modifier.constrainAs(logo){
                bottom.linkTo(forgetPasswordText.top,96.dp)
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
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)}
        )

        Text(
            text = "No worries, we’ll send you reset instructions",
            style = TextStyle(
                fontSize = 16.sp,
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
    }
}

