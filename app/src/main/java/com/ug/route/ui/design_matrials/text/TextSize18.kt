package com.ug.route.ui.design_matrials.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.ug.route.R

@Composable
fun Text18(
    modifier: Modifier = Modifier,
    fontSize: Int = 18,
    weight: Int = 500,
    text : String,
    color: Color = Color(0xFFFFFFFF),
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = 1
){

    Text(
        text = text,
        color = color,
        style = TextStyle(
            fontSize = fontSize.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontWeight = FontWeight(weight),
            textAlign = textAlign,
        ),
        modifier = modifier,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}