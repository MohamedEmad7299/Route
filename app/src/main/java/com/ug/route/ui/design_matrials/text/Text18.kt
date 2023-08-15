package com.ug.route.ui.design_matrials.text

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
    weight: Int = 500,
    text : String,
    color: Color = Color(0xFFFFFFFF)
){

    Text(
        text = text,
        color = color,
        style = TextStyle(
            fontSize = 18.sp,
            lineHeight = 18.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontWeight = FontWeight(weight),
            textAlign = TextAlign.Center,
        ),
        modifier = modifier
    )
}