package com.ug.route.ui.design_matrials.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextWithTextField(
    text : String,
    textModifier: Modifier,
    hint : String,
    value : String,
    onValueChange : (String) -> Unit,
    textFieldModifier: Modifier
){

    Text18(
        text = text,
        modifier = textModifier
    )

    StandardTextField(
        hint = hint,
        value = value,
        onValueChange = onValueChange,
        modifier = textFieldModifier
    )
}