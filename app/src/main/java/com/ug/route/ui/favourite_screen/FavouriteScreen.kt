package com.ug.route.ui.favourite_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ug.route.data.database.entities.FavouriteEntity
import com.ug.route.ui.design_matrials.text.FavouriteItem
import com.ug.route.ui.design_matrials.text.SearchBarAndCart
import com.ug.route.ui.design_matrials.text.SmallLogo
import com.ug.route.ui.no_internet_screen.NoInternetContent
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

    if (isInternetConnected(context)){


        FavouriteContent(
            screenState = screenState,
            navToSearch = {
                handelInternetError(context,
                    {navController.navigate(Screen.SearchScreen.route)},
                    viewModel::onInternetError)
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
    navToSearch : () -> Unit
){

    val favs = listOf(
        FavouriteEntity(
            0,
            "watch",
            "https://www.watchtime.com/wp-content/uploads/2021/11/Armin-Strom-TRIBUTE-1_1000-801x1024.jpg",
            1200,
            Color.DarkGray.toArgb(),
            "Gray"
        ),
        FavouriteEntity(
            0,
            "watch tany",
            "https://cdn.shopify.com/s/files/1/0020/1896/7605/files/green_watch_600x600.jpg?v=1639002196",
            1500,
            Color.Green.toArgb(),
            "Green"
        )
    )

    ConstraintLayout(
        Modifier
            .background(Color.White)
            .fillMaxSize()
    ){
        val(logo,
            searchBar,
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
            onClickCartIcon = {},
            navToSearch = {  }
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .constrainAs(items){
                top.linkTo(searchBar.bottom,16.dp)
            }
        ){
            items(favs){ favItem ->

                FavouriteItem(
                    modifier = Modifier.padding(bottom = 16.dp),
                    itemName = favItem.name,
                    imageURL = favItem.imageURL,
                    circleColor = Color(favItem.colorValue),
                    colorName = favItem.colorName,
                    itemPrice = favItem.price,
                    onClickAdd = { /*TODO*/ }) {
                }
            }
        }

    }
}