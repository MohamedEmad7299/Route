package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ug.route.R
import com.ug.route.ui.theme.DarkBlue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddAndMinusButtons(
    modifier: Modifier = Modifier,
    onClickMinus : () -> Unit,
    value : String,
    onClickAdd : () -> Unit){

    Row(modifier = modifier
        .background(DarkBlue, shape = RoundedCornerShape(20.dp))
        .width(122.dp)
        .height(42.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly){

        IconButton(
            onClick = onClickMinus,
            modifier = Modifier.size(50.dp)
        ){
            Icon(
                tint = Color.White,
                painter = painterResource(id = R.drawable.minus),
                contentDescription = "minus icon")
        }

        Text(
            text = value,
            color = Color.White,
            style = TextStyle(
                fontSize = 17.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .weight(1f),
            maxLines = 1)

        IconButton(
            onClick = onClickAdd,
            modifier = Modifier
                .size(50.dp)
        ){
            Icon(
                tint = Color.White,
                painter = painterResource(id = R.drawable.add),
                contentDescription = "add icon")
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
            value = "999",
            onClickAdd = {}
        )
    }
}