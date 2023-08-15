package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ug.route.R
import com.ug.route.ui.theme.DarkBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OneDigitTextField(
    value: String,
    isError : Boolean,
    onValueChange: (String) -> Unit
){

    OutlinedTextField(
        isError = isError,
        singleLine = true,
        modifier = Modifier
            .width(50.dp)
            .height(70.dp),
        shape =  RoundedCornerShape(8.dp),
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            cursorColor = DarkBlue,
            focusedBorderColor = DarkBlue,
        ),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontWeight = FontWeight(300),
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}