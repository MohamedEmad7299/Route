package com.ug.route.ui.categories_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
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
import com.ug.route.data.fake.FakeData
import com.ug.route.ui.design_matrials.text.CategoriesSelection
import com.ug.route.ui.design_matrials.text.CategoryBanner
import com.ug.route.ui.design_matrials.text.SearchBarAndCart
import com.ug.route.ui.design_matrials.text.SmallLogo
import com.ug.route.ui.design_matrials.text.SubcategoryItem
import com.ug.route.ui.no_internet_screen.NoInternetContent
import com.ug.route.ui.theme.DarkPurple
import com.ug.route.utils.Screen
import com.ug.route.utils.SharedPreferences
import com.ug.route.utils.handelInternetError
import com.ug.route.utils.isInternetConnected


@Composable
fun CategoriesScreen(
    navController : NavController,
    viewModel: CategoriesViewModel = hiltViewModel()
){

    val screenState by viewModel.screenState.collectAsState()
    val context = LocalContext.current

    if (isInternetConnected(context)){

        CategoriesContent(
            screenState = screenState,
            onClickCartIcon = {navController.navigate(Screen.CartScreen.route)},
            navToSearch = {
                handelInternetError(context,
                    {navController.navigate(Screen.SearchScreen.route)},
                    viewModel::onInternetError)
            },
            onCategoryChange = {
                handelInternetError(
                    context,
                    {viewModel.onCategoryChange(it)},
                    viewModel::onInternetError
                )
            },
            onClickSubcategory = { subCategoryName ->
                handelInternetError(
                    context,
                    {navController.navigate("${Screen.ProductsScreen.route}/${subCategoryName}")},
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
fun CategoriesContent(
    screenState: CategoriesState,
    onClickCartIcon : () -> Unit,
    navToSearch: () -> Unit,
    onCategoryChange : (String) -> Unit,
    onClickSubcategory: (String) -> Unit
){

    val systemUiController = rememberSystemUiController()

    ConstraintLayout(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {

        val (logo,
            searchBar,
            categoriesSelection,
            categoryBanner,
            selectedCategoryText,
            subcategoriesGrid) = createRefs()

        SideEffect {

            systemUiController.setStatusBarColor(Color.White, darkIcons = true)
        }


        CategoriesSelection(
            modifier = Modifier.constrainAs(categoriesSelection){
                top.linkTo(searchBar.bottom)
            },
            firstIndex = if (SharedPreferences.selectedCategory == null) "Music" else SharedPreferences.selectedCategory!!,
            onCategoryChange = onCategoryChange
        )

        SmallLogo(
            modifier = Modifier
                .constrainAs(logo) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(parent.top,16.dp)
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


        Text(
            text = screenState.selectedCategory,
            modifier = Modifier
                .constrainAs(selectedCategoryText){
                    top.linkTo(searchBar.bottom)
                    start.linkTo(categoriesSelection.end,16.dp)
                },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(600),
                color = DarkPurple,
                textAlign = TextAlign.Center)
        )


        CategoryBanner(
            title = screenState.selectedCategory,
            imageResource = FakeData.categoryImages[screenState.selectedCategory]!!,
            modifier = Modifier
                .width(250.dp)
                .padding(horizontal = 16.dp)
                .constrainAs(categoryBanner) {
                    top.linkTo(selectedCategoryText.bottom, 16.dp)
                    start.linkTo(categoriesSelection.end)
                    end.linkTo(parent.end)
                }
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .padding(horizontal = 80.dp)
                .constrainAs(subcategoriesGrid) {
                    top.linkTo(categoryBanner.bottom, 32.dp)
                    start.linkTo(categoriesSelection.end)
                    end.linkTo(parent.end)
                },
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            FakeData.subCategories[screenState.selectedCategory]?.let { subcategories ->
                items(subcategories) { subcategory ->
                    SubcategoryItem(
                        name = subcategory.name,
                        imageResource = subcategory.imageResource,
                        onClickItem = {
                            onClickSubcategory(subcategory.name)
                        }
                    )
                }
            }
        }
    }
}
