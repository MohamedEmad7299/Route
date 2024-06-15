package com.ug.route.ui.favourite_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.ug.route.ui.design_matrials.text.FavouriteItem
import com.ug.route.ui.design_matrials.text.SearchBarAndCart
import com.ug.route.ui.design_matrials.text.SmallLogo
import com.ug.route.ui.no_internet_screen.NoInternetContent
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.ui.theme.Gray80
import com.ug.route.utils.Screen
import com.ug.route.utils.handelInternetError
import com.ug.route.utils.isInternetConnected

@Composable
fun FavouriteScreen(
    navController : NavController,
    viewModel: FavouriteViewModel = hiltViewModel()
){

    val screenState by viewModel.screenState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getWishList()
    }

    if (isInternetConnected(context)){

        FavouriteContent(
            screenState = screenState,
            navToSearch = {
                handelInternetError(context,
                    {navController.navigate(Screen.SearchScreen.route)},
                    viewModel::onInternetError)
            },
            onClickFavButton = { favouriteItemId ->
                handelInternetError(context,{
                    viewModel.deleteWishListItem(favouriteItemId) },viewModel::onInternetError)
            },
            onClickAddButton = { itemId ->

                handelInternetError(context,{

                    viewModel.addProductToCart(itemId)
                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()

                },viewModel::onInternetError)
            },
            onClickCartIcon = {
                handelInternetError(context,
                    {navController.navigate(Screen.CartScreen.route)},
                    viewModel::onInternetError)
            },
            onClickItem = { id ->
                handelInternetError(
                    context,
                    {navController.navigate("${Screen.ProductDetailsScreen.route}/${id}")},
                    {navController.navigate(Screen.NoInternetScreen.route)}
                )
            }
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

@Composable
fun FavouriteContent(
    screenState: FavouriteState,
    navToSearch : () -> Unit,
    onClickFavButton : (String) -> Unit,
    onClickAddButton : (String) -> Unit,
    onClickCartIcon : () -> Unit,
    onClickItem: (String) -> Unit
){

    ConstraintLayout(
        Modifier
            .background(Color.White)
            .fillMaxSize()
    ){
        val(logo,
            searchBar,
            noFavsText,
            circularProgress,
            items)= createRefs()

        SmallLogo(
            modifier = Modifier
                .constrainAs(logo){
                    start.linkTo(parent.start,16.dp)
                    top.linkTo(parent.top,16.dp)
                }
        )

        SearchBarAndCart(
            modifier = Modifier.constrainAs(searchBar){
                start.linkTo(parent.start)
                top.linkTo(logo.top,32.dp)
            },
            onClickCartIcon = onClickCartIcon,
            navToSearch = navToSearch
        )

        if (screenState.isLoading){

            CircularProgressIndicator(
                modifier = Modifier
                    .constrainAs(circularProgress) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(32.dp),
                color = DarkBlue,
                strokeWidth = 5.dp
            )

        } else {

            if (screenState.favouriteProducts.isEmpty()){

                Text(
                    modifier = Modifier.constrainAs(noFavsText){
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(searchBar.bottom,256.dp)
                    },
                    text = stringResource(R.string.no_favourites_items_added),
                    style = TextStyle(
                        fontSize = 17.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(300),
                        color = Gray80,
                        textAlign = TextAlign.Center,
                    )
                )

            }
            else {

                LazyColumn(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 200.dp)
                        .constrainAs(items) {
                            top.linkTo(searchBar.bottom, 16.dp)
                            start.linkTo(parent.start, 8.dp)
                            end.linkTo(parent.end, 8.dp)
                        }
                ){
                    items(screenState.favouriteProducts){ favItem ->

                        FavouriteItem(
                            modifier = Modifier.padding(bottom = 16.dp),
                            itemName = favItem?.title!!,
                            imageURL = favItem.imageCover!!,
                            circleColor = Color.Black,
                            colorName = "Black",
                            itemPrice = favItem.price!!,
                            onClickAdd = {
                                onClickAddButton(favItem.id!!)
                            },
                            onClickFavButton = {
                                onClickFavButton(favItem.id!!)
                            },
                            onClickItem = {
                                onClickItem(favItem.id!!)
                            }
                        )
                    }
                }
            }
        }
    }
}
