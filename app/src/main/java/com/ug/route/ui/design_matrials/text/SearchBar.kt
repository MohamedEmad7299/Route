package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ug.route.R
import com.ug.route.ui.theme.DarkBlue

@Composable
fun SearchBarAndCart(
    modifier: Modifier = Modifier,
    onClickCartIcon : () -> Unit,
    navToSearch : () -> Unit
){

    Row(
        modifier
            .padding(16.dp)
            .fillMaxWidth(),
        Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(50.dp)
                .clickable(
                    onClick = navToSearch,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                )
                .background(Color.White, RoundedCornerShape(32.dp))
                .border(1.dp, DarkBlue, RoundedCornerShape(32.dp))
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp),
                text = "what do you search for?",
                color = DarkBlue,
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(300),
            )

            Icon(
                tint = DarkBlue,
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "search icon",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
            )
        }

        IconButton(
            onClick =  onClickCartIcon,
            modifier = Modifier.size(50.dp)
        ){
            Icon(
                tint = DarkBlue,
                painter = painterResource(id = R.drawable.cart),
                contentDescription = "cart icon")
        }
    }
}