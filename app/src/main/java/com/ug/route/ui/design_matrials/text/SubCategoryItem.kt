package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
import com.ug.route.ui.theme.DarkPurple


@Preview(showSystemUi = true)
@Composable
fun SubcategoryItemPreview(){

    SubcategoryItem()
}

@Composable
fun SubcategoryItem(
    modifier: Modifier = Modifier,
    name: String = "Footwear",
    imageResource: Int = R.drawable.footwear
){

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Image(
            modifier = Modifier
                .size(60.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .border(width = 1.dp, color = DarkBlue, shape = RoundedCornerShape(8.dp)),
            painter = painterResource(id = imageResource),
            contentDescription = "",
            contentScale = ContentScale.Crop)


        Text(
            text = name,
            color = DarkPurple,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .padding(top = 8.dp)
        )
    }
}