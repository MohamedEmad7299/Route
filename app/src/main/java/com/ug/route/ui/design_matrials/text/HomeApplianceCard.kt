package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ug.route.R
import com.ug.route.networking.dto_models.HomeApplianceProduct
import com.ug.route.ui.theme.DarkBlue


@Preview(showSystemUi = true)
@Composable
fun HomeApplianceCardPreview(){

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeApplianceCard(
            HomeApplianceProduct(
                image = R.drawable.ghasala,
                name = "Washing Machine",
                review = "4.8",
                price = "9000")
        )
    }
}


@Composable
fun HomeApplianceCard(
    product: HomeApplianceProduct
){

    Card(
        modifier = Modifier
            .height(264.dp)
            .width(224.dp)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, DarkBlue)
    ){

        ConstraintLayout(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ){

            val (image,
                fav,
                name,
                review,
                price,
                add) = createRefs()

            Image(
                painter = painterResource(id = product.image),
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                    }
                    .height(132.dp)
                    .fillMaxWidth(),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(40.dp)
                    .constrainAs(fav) {
                        top.linkTo(parent.top, 8.dp)
                        end.linkTo(parent.end, 8.dp)
                    }
            ){
                Box(
                    Modifier
                        .size(30.dp)
                        .background(
                            shape = CircleShape,
                            color = Color.White)
                ){

                    Icon(
                        modifier = Modifier
                            .align(Alignment.Center),
                        tint = DarkBlue,
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = "")
                }
            }

            Text18(
                modifier = Modifier.constrainAs(name){
                    start.linkTo(parent.start,8.dp)
                    top.linkTo(image.bottom,8.dp)
                },
                text = product.name,
                color = Color(0xFF06004F)
            )

            Row(
                modifier = Modifier.constrainAs(review){
                    start.linkTo(parent.start,8.dp)
                    top.linkTo(name.bottom,8.dp)
                },
                verticalAlignment = Alignment.CenterVertically
            ){
                Text18(
                    Modifier.padding(end = 8.dp),
                    text = "Review (${product.review})",
                    color = Color(0xFF06004F)
                )

                Image(
                    modifier = Modifier.size(22.dp),
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "")
            }

            Text18(
                modifier = Modifier.constrainAs(price){
                    start.linkTo(parent.start,8.dp)
                    top.linkTo(review.bottom,8.dp)
                },
                text = "${product.price} EGP",
                color = Color(0xFF06004F)
            )


            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(40.dp)
                    .constrainAs(add) {
                        end.linkTo(parent.end, 8.dp)
                        bottom.linkTo(parent.bottom, 8.dp)
                    }
            ){
                Icon(
                    modifier = Modifier
                        .background(DarkBlue),
                    tint = Color.White,
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "")
            }
        }
    }
}