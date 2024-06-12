package com.ug.route.ui.product_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ug.route.R
import com.ug.route.data.fake.FakeData
import com.ug.route.ui.design_matrials.text.ProductCard
import com.ug.route.ui.design_matrials.text.SearchBarAndCart
import com.ug.route.ui.design_matrials.text.SmallLogo
import com.ug.route.ui.no_internet_screen.NoInternetContent
import com.ug.route.ui.theme.Gray80
import com.ug.route.utils.Screen
import com.ug.route.utils.handelInternetError
import com.ug.route.utils.isInternetConnected

@Composable
fun ProductsScreen(
    navController : NavController,
    viewModel: ProductsViewModel = hiltViewModel()
){

    val screenState by viewModel.screenState.collectAsState()
    val context = LocalContext.current

    if (screenState.message.isNotEmpty()){
        LaunchedEffect(key1 = screenState.launchedEffectKey){
            Toast.makeText(context, screenState.message, Toast.LENGTH_SHORT).show()
            viewModel.stopLaunchedEffect()
        }
    }

    if (isInternetConnected(context)){

        ProductsContent(
            screenState = screenState,
            onClickCartIcon = {navController.navigate(Screen.CartScreen.route)},
            navToSearch = {
                handelInternetError(context,
                    {navController.navigate(Screen.SearchScreen.route)},
                    viewModel::onInternetError)
            },
            onClickAddButton = { productID ->
                handelInternetError(context,
                    {
                        viewModel.addProductToCart(
                            productID = productID
                        )

                    }, viewModel::onInternetError)
            },
            onClickItem = {
                handelInternetError(context,
                    {navController.navigate("${Screen.ProductDetailsScreen.route}/$it")},
                    viewModel::onInternetError)
            },
            addToWishList = { productID ->
                handelInternetError(context,
                    {
                        viewModel.addProductToWishList(
                            productID = productID
                        )
                    }, viewModel::onInternetError)
            },
            deleteFromWishList = { productID ->
                handelInternetError(context,
                    {
                        viewModel.deleteWishListItem(
                            productID = productID
                        )
                    }, viewModel::onInternetError)
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
fun ProductsContent(
    screenState: ProductsState,
    onClickCartIcon : () -> Unit,
    navToSearch : () -> Unit,
    onClickAddButton: (String) -> Unit,
    addToWishList: (String) -> Unit,
    deleteFromWishList: (String) -> Unit,
    onClickItem: (String) -> Unit
){

    val systemUiController = rememberSystemUiController()

    ConstraintLayout(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {

        val (logo,
            searchBar,
            grid,
            noProductsText) = createRefs()

        SideEffect {

            systemUiController.setStatusBarColor(Color.White, darkIcons = true)
        }

        SmallLogo(
            modifier = Modifier
                .constrainAs(logo) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(parent.top, 16.dp)
                }
        )

        SearchBarAndCart(
            modifier = Modifier.constrainAs(searchBar) {
                start.linkTo(parent.start)
                top.linkTo(logo.top, 32.dp)
            },
            onClickCartIcon = onClickCartIcon,
            navToSearch = navToSearch
        )


        if (FakeData.products.any { it.subcategory!![0]!!.id == screenState.subCategoryId }){

            var loadingProductId by remember { mutableStateOf<String?>(null) }

            if (!screenState.isLoading) loadingProductId = null

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .constrainAs(grid) {
                        top.linkTo(searchBar.bottom, 16.dp)
                        start.linkTo(parent.start, 8.dp)
                        end.linkTo(parent.end, 8.dp)
                    }
                    .padding(end = 8.dp, start = 8.dp, bottom = 144.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(FakeData.products.filter { it.subcategory!![0]!!.id ==  screenState.subCategoryId}) { product ->

                    ProductCard(
                        imageURL = product.images!![0]!!,
                        productName = product.title!!,
                        productPrice = product.price!!,
                        productReview = product.ratingsAverage.toString(),
                        onClickAddButton = {
                            loadingProductId = product.id!!
                            onClickAddButton(product.id)
                        },
                        onClickItem = {
                            onClickItem(product.id!!)
                        },
                        isLoading = loadingProductId == product.id!!,
                        isFavourite = FakeData.wishList.any{ it?.id == product.id},
                        onClickFavButton = {
                            if (FakeData.wishList.any{ it?.id == product.id}){

                                deleteFromWishList(product.id)
                            }
                            else {

                                addToWishList(product.id)
                            }
                        }
                    )
                }
            }
        } else {

            Text(
                modifier = Modifier.constrainAs(noProductsText){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(searchBar.bottom)
                },
                text = "No Products Added",
                style = TextStyle(
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(300),
                    color = Gray80,
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}
