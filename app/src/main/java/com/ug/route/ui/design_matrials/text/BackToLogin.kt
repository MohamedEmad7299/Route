package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ug.route.R
import com.ug.route.ui.theme.Gray80

@Composable
fun BackToLogin(
    modifier: Modifier = Modifier,
    onClickBack : () -> Unit
){


    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {


        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
            contentDescription = "Back Icon",
            tint = Gray80
        )

        ClickableText(
            text = AnnotatedString(" Back to log in"),
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(700),
                color = Gray80,
                textAlign = TextAlign.Center,
            )
        ){
            onClickBack()
        }
    }
}

//modifier = modifier.constrainAs(backButton){
//    top.linkTo(resetButton.bottom,32.dp)
//    start.linkTo(parent.start)
//    end.linkTo(parent.end) }