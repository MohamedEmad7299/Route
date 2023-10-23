package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ug.route.R
import com.ug.route.ui.theme.CardStrokeColor
import com.ug.route.ui.theme.DarkPurple


@Composable
fun FavouriteItem(){

    Card(
        modifier = Modifier
            .height(136.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(size = 16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = CardStrokeColor
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        ConstraintLayout(
            Modifier.fillMaxSize()
        ){
//            AsyncImage(
//                modifier = Modifier
//                    .padding(horizontal = 8.dp)
//                    .width(120.dp)
//                    .fillMaxHeight(),
//                model = "",
//                contentDescription = "",
//                contentScale = ContentScale.Crop
//            )

            val (
                image,
                name,
                circleColor,
                color,
                price,
                oldPrice,
                favButton,
                addButton
            ) = createRefs()

            Card(
                modifier = Modifier
                    .width(120.dp)
                    .padding(end = 8.dp)
                    .fillMaxHeight()
                    .constrainAs(image)
                    {
                        start.linkTo(parent.start)
                    },
                shape = RoundedCornerShape(size = 16.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = CardStrokeColor
                )
            ){
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.makwa),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                modifier = Modifier
                    .constrainAs(name)
                    {
                        start.linkTo(image.end,8.dp)
                        top.linkTo(parent.top,8.dp)
                    },
                text = "Nike Air Jordon",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(500),
                    color = DarkPurple,
                    textAlign = TextAlign.Center
                )
            )

            Image(
                modifier = Modifier
                    .constrainAs(circleColor)
                    {
                        start.linkTo(image.end,8.dp)
                        top.linkTo(name.bottom,8.dp)
                    },
                painter = painterResource(id = R.drawable.color_circle),
                contentDescription = "image description",
                contentScale = ContentScale.None
            )

            Text(
                modifier = Modifier.constrainAs(color)
                {
                    start.linkTo(circleColor.end,8.dp)
                    top.linkTo(circleColor.top)
                    bottom.linkTo(circleColor.bottom)
                },
                text = "Black color",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = DarkPurple,
                    textAlign = TextAlign.Center,
                )
            )

            Text(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .constrainAs(price)
                    {
                        start.linkTo(image.end, 8.dp)
                        top.linkTo(color.bottom, 8.dp)
                    },
                text = "EGP 1,200",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(500),
                    color = DarkPurple,
                    textAlign = TextAlign.Center,
                )
            )


            Text(
                modifier = Modifier.constrainAs(oldPrice)
                {
                    start.linkTo(image.end,8.dp)
                    top.linkTo(price.bottom)
                },
                text = "EGP 1,500",
                style = TextStyle(
                    fontSize = 11.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = DarkPurple,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.LineThrough
                )
            )


            IconButton(
                modifier = Modifier.constrainAs(favButton)
                {
                    end.linkTo(parent.end,8.dp)
                    top.linkTo(parent.top,8.dp)
                },
                onClick = { /*TODO*/ },
                colors= IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White
                )
            ){
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.fav),
                    contentDescription = "")
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(120.dp)
                    .height(36.dp)
                    .constrainAs(addButton)
                    {
                        end.linkTo(parent.end, 8.dp)
                        bottom.linkTo(parent.bottom, 8.dp)
                    },
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkPurple
                )
            ) {
                Text(
                    text = "Add to Cart",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview(){

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ){
        FavouriteItem()
    }

}