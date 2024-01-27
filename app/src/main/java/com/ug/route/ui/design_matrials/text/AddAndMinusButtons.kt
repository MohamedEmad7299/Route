package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ug.route.R
import com.ug.route.ui.theme.DarkBlue


@Composable
fun AddAndMinusButtons(
    modifier: Modifier = Modifier,
    onClickMinus : () -> Unit,
    value : String,
    onClickAdd : () -> Unit){

    Box(modifier = modifier
        .background(DarkBlue, shape = RoundedCornerShape(20.dp))
        .width(122.dp)
        .height(42.dp))
    {
        Row(
            Modifier.fillMaxSize()
        ){

            IconButton(
                onClick = onClickMinus,
                modifier = Modifier.size(50.dp)
            ){
                Icon(
                    tint = Color.White,
                    painter = painterResource(id = R.drawable.minus),
                    contentDescription = "minus icon")
            }

            Text18(
                modifier = Modifier.padding(8.dp),
                text = value
            )

            IconButton(
                onClick = onClickAdd,
                modifier = Modifier.size(50.dp)
            ){
                Icon(
                    tint = Color.White,
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "add icon")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AddAndMinusButtonsPreview(){

    Column(
        Modifier.fillMaxSize()
    ){
        AddAndMinusButtons(
            onClickMinus = {},
            value = "1",
            onClickAdd = {}
        )
    }
}