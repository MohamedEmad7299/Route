package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ug.route.R

@Composable
fun Logo(
    modifier: Modifier = Modifier
){

    Image(

        painter = painterResource(id = R.drawable.logo),
        contentDescription = "",
        modifier = modifier
            .padding(horizontal = 64.dp)
    )
}