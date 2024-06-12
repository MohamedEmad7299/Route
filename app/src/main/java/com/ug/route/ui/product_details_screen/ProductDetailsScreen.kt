package com.ug.route.ui.product_details_screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ug.route.R
import com.ug.route.data.fake.FakeData
import com.ug.route.networking.dto_models.products.Product
import com.ug.route.ui.design_matrials.text.AddAndMinusButtons
import com.ug.route.ui.design_matrials.text.FiveColors
import com.ug.route.ui.no_internet_screen.NoInternetContent
import com.ug.route.ui.theme.CardStrokeColor
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.ui.theme.DarkPurple
import com.ug.route.ui.theme.DarkPurple60
import com.ug.route.ui.theme.LightPurple
import com.ug.route.utils.Screen
import com.ug.route.utils.handelInternetError
import com.ug.route.utils.isInternetConnected
import java.text.NumberFormat
import java.util.Locale
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.*
import com.ug.route.networking.ProductCount
import com.ug.route.ui.design_matrials.text.SliderBannerAsync

@Composable
fun ProductDetailsScreen(
    navController : NavController,
    viewModel: ProductDetailsViewModel = hiltViewModel()
){

    val context = LocalContext.current
    val screenState by viewModel.screenState.collectAsState()


    if (screenState.message.isNotEmpty()){
        LaunchedEffect(key1 = screenState.launchedEffectKey){
            Toast.makeText(context, screenState.message, Toast.LENGTH_SHORT).show()
        }
    }

    if (isInternetConnected(context)){

        ProductDetailsContent(
            onClickSearchIcon = {
                handelInternetError(context,
                    {navController.navigate(Screen.SearchScreen.route)},
                    viewModel::onInternetError)
            },
            onClickBackArrow = {
                handelInternetError(context,
                    {navController.popBackStack() },
                    viewModel::onInternetError)
            },
            onClickCartIcon = {
                handelInternetError(context,
                    {navController.navigate(Screen.CartScreen.route)},
                    viewModel::onInternetError)
            },
            onClickFavouriteIcon = { product ->
                handelInternetError(context,
                    {
                        viewModel.onClickFavIcon()
                        if (screenState.isFavourite) viewModel.deleteWishListItem(product.id!!)
                        else viewModel.addProductToWishList(product.id!!)

                    },
                    viewModel::onInternetError)
            } ,
            onClickAddToCart = { product ->
                handelInternetError(context,
                    {viewModel.addProductToCart(product.id!!,{ navController.popBackStack() } , ProductCount(screenState.counter.toString())) },
                    viewModel::onInternetError)
            },
            screenState = screenState,
            onClickPlus = viewModel::onClickPlus,
            onClickMinus = viewModel::onClickMinus
        )

    } else NoInternetContent{

        if (isInternetConnected(context)){

            navController.navigate(Screen.HomeScreen.route){
                popUpTo(navController.graph.id){
                    inclusive = true
                }
            }
        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductDetailsContent(
    screenState: ProductDetailsState,
    onClickCartIcon: () -> Unit,
    onClickPlus: () -> Unit,
    onClickMinus: () -> Unit,
    onClickSearchIcon: () -> Unit,
    onClickBackArrow: () -> Unit,
    onClickAddToCart: (Product) -> Unit,
    onClickFavouriteIcon:(Product) -> Unit
) {

    val systemUiController = rememberSystemUiController()

    val product = FakeData.products.filter { it.id == screenState.productId }[0]

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

        IconButton(
            modifier = Modifier
                .constrainAs(backArrow){
                    start.linkTo(parent.start)
                    top.linkTo(parent.top,8.dp)
                },
            onClick = onClickBackArrow){

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
            text = "Product Details",
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
            modifier = Modifier.constrainAs(cartIcon){
                end.linkTo(parent.end,8.dp)
                top.linkTo(parent.top,8.dp)
            },
            onClick = onClickCartIcon
        ) {
            Icon(
                tint = DarkBlue,
                painter = painterResource(id = R.drawable.cart),
                contentDescription = "")
        }


        IconButton(
            modifier = Modifier.constrainAs(searchIcon){
                end.linkTo(cartIcon.start)
                top.linkTo(parent.top,8.dp)
            },
            onClick = onClickSearchIcon
        ) {
            Icon(
                tint = DarkBlue,
                painter = painterResource(id = R.drawable.icon_search),
                contentDescription = "")
        }

        SliderBannerAsync(
            modifier = Modifier.constrainAs(productImage){
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(productDetailsText.bottom, 16.dp)
            },
            onClickOffer = {},
            models = product.images!!)


        IconButton(
            modifier = Modifier
                .constrainAs(favButton) {
                end.linkTo(productImage.end, 16.dp)
                top.linkTo(productImage.top)
            },
            onClick = {
                onClickFavouriteIcon(product)
            }
        ){
            Image(
                modifier = Modifier
                    .size(40.dp),
                painter = if (screenState.isFavourite) painterResource(id = R.drawable.fav_focus) else painterResource(id = R.drawable.fav_unfocus),
                contentDescription = "")
        }


        Text(
            modifier = Modifier
                .padding(start = 16.dp , end = 116.dp)
                .constrainAs(productName){
                    start.linkTo(parent.start)
                    top.linkTo(productImage.bottom,16.dp)
                },
            text = product.title!!,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(500),
                color = DarkPurple,
                textAlign = TextAlign.Center,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            modifier = Modifier
                .constrainAs(productPrice){
                    end.linkTo(parent.end,16.dp)
                    top.linkTo(productImage.bottom,16.dp)
                },
            text = "EGP ${NumberFormat.getNumberInstance(Locale.US).format(product.price!!)}",
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
                    text = "${NumberFormat.getNumberInstance(Locale.US).format(product.sold)} sold",
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

        AddAndMinusButtons(
            modifier = Modifier.constrainAs(countButton){
                end.linkTo(parent.end,16.dp)
                top.linkTo(productPrice.bottom,16.dp)
            },
            onClickMinus = {
                if (screenState.counter > 1) onClickMinus()
            },
            onClickAdd = {
                if (screenState.counter < 99) onClickPlus()
            },
            value = screenState.counter
        )


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
                text = "${product.ratingsAverage} (${NumberFormat.getNumberInstance(Locale.US).format(product.ratingsQuantity)})",
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
                .padding(end = 24.dp)
                .constrainAs(descriptionContent) {
                    start.linkTo(parent.start , 16.dp)
                    top.linkTo(descriptionText.bottom, 8.dp)
                },
            text = if (product.description!!.length > 150)
                "${product.description.take(150)}..."
            else product.description,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(500),
                color = DarkPurple60,
            ),
            textAlign = TextAlign.Start
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


        Text(
            modifier = Modifier
                .constrainAs(priceText){
                    start.linkTo(parent.start,16.dp)
                    top.linkTo(totalPriceText.bottom)
                },
            text = "EGP ${NumberFormat.getNumberInstance(Locale.US).format(product.price*screenState.counter)}",
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
            onClick = {
                onClickAddToCart(product)
            },
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
