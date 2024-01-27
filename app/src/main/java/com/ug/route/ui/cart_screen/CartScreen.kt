package com.ug.route.ui.cart_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.ug.route.R
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.ui.theme.DarkPurple
import com.ug.route.ui.theme.Gray80
import com.ug.route.ui.theme.LightPurple


@Preview(showSystemUi = true)
@Composable
fun CartScreen(){

    CartContent()
}

@Composable
fun CartContent(

) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        val (
            cartText,
            backArrow,
            totalPriceText,
            priceText,
            checkOutButton,
            items,
            noItemsText
        ) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(cartText){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top,16.dp)
                },
            text = "Cart",
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(500),
                color = DarkPurple,
                textAlign = TextAlign.Center,
            )
        )

        IconButton(
            modifier = Modifier
                .constrainAs(backArrow){
                    start.linkTo(parent.start)
                    top.linkTo(parent.top,8.dp)
                },
            onClick = {}){

            Icon(
                tint = DarkBlue,
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "")
        }

        Text(
            modifier = Modifier
                .constrainAs(totalPriceText){
                    start.linkTo(parent.start,16.dp)
                    bottom.linkTo(parent.bottom,56.dp)
                },
            text = "Total price",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(500),
                color = LightPurple,
                textAlign = TextAlign.Center,
            )
        )

        Text(
            modifier = Modifier
                .constrainAs(priceText){
                    start.linkTo(parent.start,16.dp)
                    top.linkTo(totalPriceText.bottom)
                },
            text = "EGP 3,500",
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
                .constrainAs(checkOutButton) {
                    end.linkTo(parent.end, 16.dp)
                    bottom.linkTo(parent.bottom, 32.dp)
                },
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkBlue
            ),
            shape = RoundedCornerShape(20.dp),
        ) {
            Text(
                text = stringResource(R.string.check_out),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(500),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            )

            Icon(
                modifier = Modifier.padding(start = 16.dp),
                tint = Color.White,
                painter = painterResource(id = R.drawable.small_arrow),
                contentDescription = "")
        }


        if (true){

            Text(
                modifier = Modifier.constrainAs(noItemsText){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
                text = "No Items Added",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(300),
                    color = Gray80,
                    textAlign = TextAlign.Center,
                )
            )

        } else {

//            LazyColumn(
//                modifier = Modifier
//                    .padding(horizontal = 16.dp)
//                    .fillMaxSize()
//                    .constrainAs(items) {
//                        top.linkTo(searchBar.bottom, 16.dp)
//                    }
//            ) {
//                items(screenState.favouriteProducts) { favItem ->
//
//                    FavouriteItem(
//                        modifier = Modifier.padding(bottom = 16.dp),
//                        itemName = favItem.name,
//                        imageURL = favItem.imageURL,
//                        circleColor = Color(favItem.colorValue),
//                        colorName = favItem.colorName,
//                        itemPrice = favItem.price,
//                        onClickAdd = { /*TODO*/ },
//                        onClickFavButton = {
//                            onClickFavButton(favItem)
//                        }
//                    )
//                }
//            }
        }
    }
}
