package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StandardButton(
    modifier : Modifier = Modifier,
    buttonColor : Color = Color.White,
    onClick : () -> Unit,
    content: @Composable RowScope.() -> Unit,
){

    Button(
        modifier = modifier
            .height(64.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        onClick = onClick ,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ),
        shape = RoundedCornerShape(16.dp),
        content = content
    )
}