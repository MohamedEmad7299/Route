package com.ug.route.design_matrials.text

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ug.route.R

@Composable
fun Text18(

    modifier: Modifier = Modifier,
    text : String
){

    Text(
        text = text,
        style = TextStyle(
            fontSize = 18.sp,
            lineHeight = 18.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontWeight = FontWeight(500),
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Center,
        ),
        modifier = modifier
    )
}