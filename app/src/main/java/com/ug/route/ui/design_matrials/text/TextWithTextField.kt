package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun TextWithTextField(
    text : String,
    textColor : Color = Color.White,
    textModifier: Modifier,
    hint : String,
    value : String,
    onValueChange : (String) -> Unit,
    textFieldModifier: Modifier,
    isError : Boolean = false,
    errorMessage : String,
    errorModifier : Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp)
){

    Text18(
        color = textColor,
        text = text,
        modifier = textModifier
    )

    StandardTextField(
        isError = isError,
        hint = hint,
        value = value,
        onValueChange = onValueChange,
        modifier = textFieldModifier,
        shape = shape
    )

    Text(
        text = errorMessage,
        color = Color.Red,
        modifier = errorModifier.alpha(if (isError) 1f else 0f)
    )
}