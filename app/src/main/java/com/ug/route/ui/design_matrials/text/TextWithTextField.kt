package com.ug.route.ui.design_matrials.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color

@Composable
fun TextWithTextField(
    text : String,
    textModifier: Modifier,
    hint : String,
    value : String,
    onValueChange : (String) -> Unit,
    textFieldModifier: Modifier,
    isError : Boolean = false,
    errorMessage : String,
    errorModifier : Modifier = Modifier,
    errorVisibility : Boolean
){

    Text18(
        text = text,
        modifier = textModifier
    )

    StandardTextField(
        isError = isError,
        hint = hint,
        value = value,
        onValueChange = onValueChange,
        modifier = textFieldModifier
    )

    Text(
        text = errorMessage,
        color = Color.Red,
        modifier = errorModifier.alpha(if (errorVisibility) 1f else 0f)
    )
}