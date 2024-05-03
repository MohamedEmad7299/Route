package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import androidx.constraintlayout.compose.ConstraintLayout
import com.ug.route.R
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.ui.theme.DarkPurple


@Preview(showSystemUi = true)
@Composable
fun CategoryBannerPreview(){

    Column(
        modifier = Modifier.padding(32.dp)
    ){
        CategoryBanner()
    }
}

@Composable
fun CategoryBanner(
    modifier : Modifier = Modifier,
    imageResource : Int = R.drawable.mens_fashion,
    title : String = "SuperMarket"
){

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(10.dp)
    ){
        ConstraintLayout(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ){

            val (
                titleText,
                shopNowWidget,
            ) = createRefs()

            Image(
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = imageResource),
                contentDescription = "category ")


            Spacer(
                modifier = Modifier
                    .background(Color.White.copy(alpha = 0.3f))
                    .fillMaxSize()
            )

            Text(
                text = title,
                modifier = Modifier
                    .width(126.dp)
                    .constrainAs(titleText) {
                        top.linkTo(parent.top, 16.dp)
                        start.linkTo(parent.start, 16.dp)
                    },
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(1000),
                    color = DarkPurple,
                    textAlign = TextAlign.Center)
            )

            Column(
                modifier = Modifier
                    .width(120.dp)
                    .height(40.dp)
                    .background(DarkBlue , RoundedCornerShape(10.dp))
                    .constrainAs(shopNowWidget){
                        bottom.linkTo(parent.bottom, 16.dp)
                        start.linkTo(parent.start, 16.dp)
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Shop Now",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(500),
                        color = Color.White,
                        textAlign = TextAlign.Center)
                )
            }
        }
    }
}