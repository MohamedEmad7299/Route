package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun ResetBoxes(
    modifier: Modifier = Modifier,
    digit1 : String,
    isError : Boolean,
    onDigit1Change : (String) -> Unit,
    digit2 : String,
    onDigit2Change : (String) -> Unit,
    digit3 : String,
    onDigit3Change : (String) -> Unit,
    digit4 : String,
    onDigit4Change : (String) -> Unit,
    digit5 : String,
    onDigit5Change : (String) -> Unit,
    digit6 : String,
    onDigit6Change : (String) -> Unit,
){

    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        Arrangement.SpaceEvenly,
    ){

        OneDigitTextField(
            value = digit1,
            isError = isError,
            onValueChange = onDigit1Change)

        OneDigitTextField(
            value = digit2,
            isError = isError,
            onValueChange = onDigit2Change)

        OneDigitTextField(
            value = digit3,
            isError = isError,
            onValueChange = onDigit3Change)

        OneDigitTextField(
            value = digit4,
            isError = isError,
            onValueChange = onDigit4Change)

        OneDigitTextField(
            value = digit5,
            isError = isError,
            onValueChange = onDigit5Change)

        OneDigitTextField(
            value = digit6,
            isError = isError,
            onValueChange = onDigit6Change)
    }
}