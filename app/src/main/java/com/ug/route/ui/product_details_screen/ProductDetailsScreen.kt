package com.ug.route.ui.product_details_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ug.route.R
import com.ug.route.ui.design_matrials.text.AddAndMinusButtons
import com.ug.route.ui.design_matrials.text.FiveColors
import com.ug.route.ui.theme.CardStrokeColor
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.ui.theme.DarkPurple
import com.ug.route.ui.theme.DarkPurple60
import com.ug.route.ui.theme.LightPurple
import java.text.NumberFormat
import java.util.Locale
import kotlin.random.Random


@Composable
fun ProductDetailsScreen(
    navController : NavController,
){

    val context = LocalContext.current

    ProductDetailsContent()
}

@Composable
fun ProductDetailsContent() {

    val systemUiController = rememberSystemUiController()

    ConstraintLayout(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {

        SideEffect {

            systemUiController.setStatusBarColor(Color.White, darkIcons = true)
        }

        val (
            backArrow,
            productDetailsText,
            searchIcon,
            cartIcon,
            productImage,
            favButton,
            productName,
            productPrice,
            soldNum,
            rate,
            countButton,
            descriptionText,
            descriptionContent,
            colorText,
            fiveColors,
            totalPriceText
        ) = createRefs()


        val (
            priceText,
            addToCartButton
        ) = createRefs()

        // req fun
        IconButton(
            modifier = Modifier
                .constrainAs(backArrow){
                    start.linkTo(parent.start)
                    top.linkTo(parent.top,8.dp)
                },
            onClick = { }){

            Icon(
                tint = DarkBlue,
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "")
        }

        Text(
            modifier = Modifier
                .constrainAs(productDetailsText){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top,16.dp)
                },
            text = "CartItem Details",
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(500),
                color = DarkPurple,
                textAlign = TextAlign.Center,
            )
        )

        // req fun
        IconButton(
            modifier = Modifier.constrainAs(cartIcon){
                end.linkTo(parent.end,8.dp)
                top.linkTo(parent.top,8.dp)
            },
            onClick = {  }
        ) {
            Icon(
                tint = DarkBlue,
                painter = painterResource(id = R.drawable.cart),
                contentDescription = "")
        }

        // req fun
        IconButton(
            modifier = Modifier.constrainAs(searchIcon){
                end.linkTo(cartIcon.start)
                top.linkTo(parent.top,8.dp)
            },
            onClick = {  }
        ) {
            Icon(
                tint = DarkBlue,
                painter = painterResource(id = R.drawable.icon_search),
                contentDescription = "")
        }

        // req image
        Card(
            modifier = Modifier
                .height(300.dp)
                .constrainAs(productImage) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(productDetailsText.bottom, 16.dp)
                }
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(size = 16.dp),
            border = BorderStroke(
                width = 1.dp,
                color = CardStrokeColor
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.dell),
                contentDescription = "product image",
                contentScale = ContentScale.Crop)
        }


        // req fun and boolean value
        IconButton(
            modifier = Modifier
                .constrainAs(favButton) {
                end.linkTo(productImage.end, 16.dp)
                top.linkTo(productImage.top)
            },
            onClick = { }
        ){
            Image(
                modifier = Modifier
                    .size(40.dp),
                painter = if (false) painterResource(id = R.drawable.fav_focus) else painterResource(id = R.drawable.fav_unfocus),
                contentDescription = "")
        }

        // req text
        Text(
            modifier = Modifier
                .constrainAs(productName){
                    start.linkTo(parent.start,16.dp)
                    top.linkTo(productImage.bottom,16.dp)
                },
            text = "Dell Laptop",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(500),
                color = DarkPurple,
                textAlign = TextAlign.Center,
            )
        )

        // req text
        Text(
            modifier = Modifier
                .constrainAs(productPrice){
                    end.linkTo(parent.end,16.dp)
                    top.linkTo(productImage.bottom,16.dp)
                },
            text = "EGP ${NumberFormat.getNumberInstance(Locale.US).format(60000)}",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(500),
                color = DarkPurple,
                textAlign = TextAlign.Center,
            )
        )


        Card(
            modifier = Modifier
                .height(40.dp)
                .width(100.dp)
                .constrainAs(soldNum) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(productName.bottom, 16.dp)
                },
            shape = RoundedCornerShape(size = 20.dp),
            border = BorderStroke(
                width = 1.dp,
                color = CardStrokeColor
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
                    text = "${NumberFormat.getNumberInstance(Locale.US).format(Random.nextInt(0, 10001))} sold",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(500),
                        color = DarkPurple,
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }

        // req 2 fun and value
        AddAndMinusButtons(
            modifier = Modifier.constrainAs(countButton){
                end.linkTo(parent.end,16.dp)
                top.linkTo(productPrice.bottom,16.dp)
            },
            onClickMinus = { /*TODO*/ },
            onClickAdd = { /*TODO*/ },
            value = 1)


        // req text
        Row(
            Modifier.constrainAs(rate){
                start.linkTo(soldNum.end,8.dp)
                top.linkTo(productName.bottom,28.dp)
            },
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(18.dp),
                painter = painterResource(id = R.drawable.star),
                contentDescription = "")

            Text(
                text = "4.8 (${NumberFormat.getNumberInstance(Locale.US).format(Random.nextInt(0, 10001))})",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(500),
                    color = DarkPurple,
                    textAlign = TextAlign.Center,
                )
            )
        }

        Text(
            modifier = Modifier
                .constrainAs(descriptionText){
                    start.linkTo(parent.start,16.dp)
                    top.linkTo(soldNum.bottom,16.dp)
                },
            text = "Description",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(500),
                color = DarkPurple,
                textAlign = TextAlign.Center,
            )
        )

        Text(
            modifier = Modifier
                .constrainAs(descriptionContent) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(descriptionText.bottom, 8.dp)
                }
                .padding(start = 16.dp, end = 32.dp),
            text = "I don't have more time to spend on creating another set of fake data, so I won't write a brief description. Please consider these words as a placeholder.",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(500),
                color = DarkPurple60,
            )
        )

        Text(
            modifier = Modifier
                .constrainAs(colorText){
                    start.linkTo(parent.start,16.dp)
                    top.linkTo(descriptionContent.bottom,16.dp)
                },
            text = "Color",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(500),
                color = DarkPurple,
                textAlign = TextAlign.Center,
            )
        )

        // req kaza 7aga
        FiveColors(
            modifier = Modifier.constrainAs(fiveColors){
                start.linkTo(parent.start,8.dp)
                top.linkTo(colorText.bottom,8.dp)
            }
        )

        Text(
            modifier = Modifier
                .constrainAs(totalPriceText){
                    start.linkTo(parent.start,16.dp)
                    top.linkTo(fiveColors.bottom,32.dp)
                },
            text = stringResource(R.string.total_price),
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(500),
                color = LightPurple,
                textAlign = TextAlign.Center,
            )
        )

        // req
        Text(
            modifier = Modifier
                .constrainAs(priceText){
                    start.linkTo(parent.start,16.dp)
                    top.linkTo(totalPriceText.bottom)
                },
            text = "EGP ${NumberFormat.getNumberInstance(Locale.US).format(60000)}",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(500),
                color = DarkPurple,
                textAlign = TextAlign.Center,
            )
        )


        Button(
            modifier = Modifier
                .width(240.dp)
                .height(48.dp)
                .constrainAs(addToCartButton) {
                    end.linkTo(parent.end, 16.dp)
                    top.linkTo(fiveColors.bottom, 32.dp)
                },
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkBlue
            ),
            shape = RoundedCornerShape(20.dp),
        ) {

            Icon(
                modifier = Modifier.padding(end = 16.dp),
                tint = Color.White,
                painter = painterResource(id = R.drawable.cart_2),
                contentDescription = "")

            Text(
                text = "Add to Cart",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(500),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Prevv(){

    ProductDetailsContent()
}
