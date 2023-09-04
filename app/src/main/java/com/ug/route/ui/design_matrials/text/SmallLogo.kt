package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.ug.route.R

@Composable
fun SmallLogo(
    modifier: Modifier = Modifier
){

    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.logo_small),
        contentDescription = "logo icon")
}