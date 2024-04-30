package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ug.route.R
import com.ug.route.ui.theme.DarkGreen
import com.ug.route.ui.theme.DarkRed
import com.ug.route.ui.theme.HappyBlue

@Composable
fun FiveColors(
    modifier: Modifier = Modifier
){

    Row(
        modifier = modifier
    ){
        var selectedCircle by remember { mutableStateOf(-1) }

        val colors = listOf(
            Color.Black,
            Color.Gray,
            HappyBlue,
            DarkRed,
            DarkGreen
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 4.dp)
        ) {

            for (i in 0..4){

                CircleWithIcon(
                    color = colors[i],
                    isSelected = selectedCircle == i,
                    onClick = { selectedCircle = i }
                )
            }
        }
    }
}

@Composable
fun CircleWithIcon(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .size(35.dp)
            .clip(CircleShape)
            .background(color = color)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {

            Icon(
                modifier = Modifier.padding(end = 16.dp),
                tint = Color.White,
                painter = painterResource(id = R.drawable.right_sign),
                contentDescription = "")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PrevU(){

    FiveColors()
}