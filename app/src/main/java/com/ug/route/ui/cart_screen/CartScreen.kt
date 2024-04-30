package com.ug.route.ui.cart_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ug.route.R
import com.ug.route.data.database.entities.CartEntity
import com.ug.route.ui.design_matrials.text.CartItem
import com.ug.route.ui.no_internet_screen.NoInternetContent
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.ui.theme.DarkPurple
import com.ug.route.ui.theme.Gray80
import com.ug.route.ui.theme.LightPurple
import com.ug.route.utils.Screen
import com.ug.route.utils.handelInternetError
import com.ug.route.utils.isInternetConnected
import java.text.NumberFormat
import java.util.Locale


@Composable
fun CartScreen(
    navController : NavController,
    viewModel: CartViewModel = hiltViewModel()
){

    val context = LocalContext.current
    val screenState by viewModel.screenState.collectAsState()

    if (isInternetConnected(context)){

        CartContent(
            onClickBackArrow = { navController.popBackStack() },
            onClickCheckOut = {
                handelInternetError(context,{
                    if (screenState.cartItems.isNotEmpty()){
                        viewModel.deleteAllCartItems()
                        viewModel.makeTotalPriceZero()
                        Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                    }
                },viewModel::onInternetError)
            },
            deleteCartItem = {
                handelInternetError(context,
                    {viewModel.deleteCartItem(it)},viewModel::onInternetError)
            },
            screenState = screenState,
            updateCartItem = {
                handelInternetError(context,{viewModel.updateCartItem(it)},viewModel::onInternetError)
            },
            onClickSearch = {
                handelInternetError(context,{navController.navigate(Screen.SearchScreen.route)},viewModel::onInternetError)
            }
        )

    } else {

        NoInternetContent {

            if (isInternetConnected(context)) {

                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
        }
    }
}

@Composable
fun CartContent(
    onClickBackArrow: () -> Unit,
    onClickCheckOut: () -> Unit,
    onClickSearch: () -> Unit,
    updateCartItem: (CartEntity) -> Unit,
    deleteCartItem: (CartEntity) -> Unit,
    screenState: CartState
){

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        val (
            cartText,
            searchIcon,
            backArrow,
            totalPriceText,
            priceText,
            checkOutButton,
            items,
            noItemsText
        ) = createRefs()



        IconButton(
            modifier = Modifier.constrainAs(searchIcon){
                end.linkTo(parent.end,8.dp)
                top.linkTo(parent.top,8.dp)
            },
            onClick = onClickSearch
        ) {
            Icon(
                tint = DarkBlue,
                painter = painterResource(id = R.drawable.icon_search),
                contentDescription = "")
        }




        Text(
            modifier = Modifier
                .constrainAs(cartText){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top,16.dp)
                },
            text = stringResource(R.string.cart),
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
            onClick = onClickBackArrow){

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
            text = "EGP ${NumberFormat.getNumberInstance(Locale.US).format(screenState.totalPrice)}",
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
                .width(200.dp)
                .height(48.dp)
                .constrainAs(checkOutButton) {
                    end.linkTo(parent.end, 16.dp)
                    bottom.linkTo(parent.bottom, 32.dp)
                },
            onClick = onClickCheckOut,
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


        if (screenState.cartItems.isEmpty()){

            Text(
                modifier = Modifier.constrainAs(noItemsText){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
                text = stringResource(R.string.no_items_added),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(300),
                    color = Gray80,
                    textAlign = TextAlign.Center,
                )
            )

        } else {

            LazyColumn(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 164.dp)
                    .constrainAs(items) {
                        top.linkTo(cartText.bottom, 16.dp)
                    }
            ) {
                items(screenState.cartItems){ cartItem ->

                    CartItem(
                        itemName = cartItem.name,
                        imageResource = cartItem.imageResource,
                        circleColor = Color(cartItem.colorValue),
                        colorName = cartItem.colorName,
                        itemPrice = cartItem.price,
                        count = cartItem.count,
                        onClickAdd = {
                            if (cartItem.count < 99){
                                updateCartItem(cartItem.copy(count = cartItem.count+1))
                            }
                        },
                        onClickMinus = {
                            if (cartItem.count > 1){
                                updateCartItem(cartItem.copy(count = cartItem.count-1))
                            }
                        },
                        onClickDelete = { deleteCartItem(cartItem) })
                }
            }
        }
    }
}
