package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ug.route.ui.theme.CardStrokeColor


@Preview(showSystemUi = true)
@Composable
fun FavouriteItem(){

    Card(
        modifier = Modifier
            .border(width = 1.dp,
                color = CardStrokeColor,
                shape = RoundedCornerShape(size = 15.dp))
            .height(114.dp)
            .fillMaxWidth(),

    ){

    }
}