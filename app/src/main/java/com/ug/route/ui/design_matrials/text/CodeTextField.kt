package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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

@Composable
fun CodeTextField(
    modifier: Modifier = Modifier,
    value : String,
    onValueChange : (String) -> Unit,
    isError : Boolean = false
){

    OutlinedTextField(
        isError = isError,
        singleLine = true,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        value = value,
        onValueChange = {newValue ->

            if (newValue.all { it.isDigit() }) onValueChange(newValue)
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            errorContainerColor = Color.White,
            disabledContainerColor = Color.White,
            cursorColor = DarkBlue,
            focusedBorderColor = DarkBlue,
        ),
        textStyle = TextStyle(
            color = DarkBlue,
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontWeight = FontWeight(1000),
            textAlign = TextAlign.Center,
            letterSpacing = 10.sp
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}