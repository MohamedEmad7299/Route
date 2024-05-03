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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.ug.route.data.database.entities.CartEntity
import com.ug.route.data.database.entities.FavouriteEntity
import com.ug.route.data.database.entities.ProductEntity
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

    if (isInternetConnected(context)){

        ProductsContent(
            screenState = screenState,
            onClickCartIcon = {navController.navigate(Screen.CartScreen.route)},
            navToSearch = {
                handelInternetError(context,
                    {navController.navigate(Screen.SearchScreen.route)},
                    viewModel::onInternetError)
            },
            onClickFavButton = { product , favouriteProduct ->
                handelInternetError(context,
                    {
                        viewModel.updateProduct(product.copy(isFavourite = !product.isFavourite))
                        if (product.isFavourite) viewModel.deleteFavouriteProduct(favouriteProduct)
                        else viewModel.addToFavourite(favouriteProduct.copy(review = product.review))
                    },
                    viewModel::onInternetError)
            },
            onClickAddButton = { product , cartItem ->
                handelInternetError(context,
                    {
                        if (viewModel.getCartItemExists(product.id))

                            viewModel.getCartItemAndUpdateIt(product.id)

                        else viewModel.insertCartItem(cartItem)

                        Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()

                    }, viewModel::onInternetError)
            },
            onClickItem = {
                handelInternetError(context,
                    {navController.navigate("${Screen.ProductDetailsScreen.route}/$it")},
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
fun ProductsContent(
    screenState: ProductsState,
    onClickCartIcon : () -> Unit,
    navToSearch : () -> Unit,
    onClickFavButton: (ProductEntity,FavouriteEntity) -> Unit,
    onClickAddButton: (ProductEntity,CartEntity) -> Unit,
    onClickItem: (Long) -> Unit
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

        if (screenState.productKey == "Laptops"){

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

                items(screenState.products) { product ->

                    ProductCard(
                        isFavourite = product.isFavourite,
                        imageResource = product.imageResource,
                        productName = product.name,
                        productPrice = product.price,
                        productReview = product.review,
                        onClickFavButton = {
                            onClickFavButton(product,FavouriteEntity(
                                id = product.id,
                                name = product.name,
                                imageResource = product.imageResource,
                                price = product.price,
                                colorValue = R.color.black,
                                colorName = "Black"
                            ))
                        },
                        onClickAddButton = {
                            onClickAddButton(
                                product,
                                CartEntity(
                                    id = product.id,
                                    name = product.name,
                                    imageResource = product.imageResource,
                                    price = product.price,
                                    colorValue = R.color.black,
                                    colorName = "Black",
                                    count = 1
                                )
                            )
                        },
                        onClickItem = {
                            onClickItem(product.id)
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
