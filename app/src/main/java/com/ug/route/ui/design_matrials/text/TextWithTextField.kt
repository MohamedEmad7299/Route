package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
    modifier: Modifier,
    hint : String,
    value : String,
    onValueChange : (String) -> Unit,
    isError : Boolean = false,
    errorMessage : String,
    shape: Shape = RoundedCornerShape(16.dp)
){

    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ){
        Text18(
            color = textColor,
            text = text
        )

        StandardTextField(
            modifier = Modifier.padding(top = 16.dp),
            isError = isError,
            hint = hint,
            value = value,
            onValueChange = onValueChange,
            shape = shape
        )

        Text(
            text = errorMessage,
            color = Color.Red,
            modifier = Modifier
                .padding(top = 8.dp)
                .alpha(if (isError) 1f else 0f)
        )
    }
}