package com.ug.route.ui.design_matrials.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextWithPasswordTextField(
    text : String,
    textModifier : Modifier = Modifier,
    hint : String,
    value : String,
    onValueChange : (String) -> Unit,
    passwordVisibility : Boolean,
    onClickVisibilityIcon : () -> Unit,
    onChangePasswordVisibility : (Boolean) -> Int,
    textFieldModifier : Modifier = Modifier
){

    Text18(
        text = text,
        modifier = textModifier
    )

    PasswordTextField(
        hint = hint,
        value = value,
        onValueChange = onValueChange,
        passwordVisibility = passwordVisibility,
        onClickVisibilityIcon = onClickVisibilityIcon,
        onChangePasswordVisibility = onChangePasswordVisibility,
        modifier = textFieldModifier
    )
}