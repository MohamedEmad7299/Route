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
fun TextWithPasswordTextField(
    text : String,
    textColor : Color = Color.White,
    textModifier : Modifier = Modifier,
    hint : String,
    value : String,
    onValueChange : (String) -> Unit,
    passwordVisibility : Boolean,
    onClickVisibilityIcon : () -> Unit,
    onChangeVisibility : (Boolean) -> Int,
    textFieldModifier : Modifier = Modifier,
    isError : Boolean = false,
    errorMessage : String,
    errorModifier : Modifier = Modifier,
    errorVisibility : Boolean,
    shape: Shape = RoundedCornerShape(16.dp)
){

    Text18(
        text = text,
        modifier = textModifier,
        color = textColor
    )

    PasswordTextField(
        isError =isError,
        hint = hint,
        value = value,
        onValueChange = onValueChange,
        passwordVisibility = passwordVisibility,
        onClickVisibilityIcon = onClickVisibilityIcon,
        onChangePasswordVisibility = onChangeVisibility,
        modifier = textFieldModifier,
        shape = shape
    )

    Text(
        text = errorMessage,
        color = Color.Red,
        modifier = errorModifier.alpha(if (errorVisibility) 1f else 0f)
    )
}