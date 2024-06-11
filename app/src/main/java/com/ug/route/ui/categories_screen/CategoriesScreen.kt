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
import com.ug.route.networking.dto_models.categories.Category
import com.ug.route.networking.dto_models.sub_categories.SubCategory
import com.ug.route.ui.design_matrials.text.CategoriesSelection
import com.ug.route.ui.design_matrials.text.CategoryBanner
import com.ug.route.ui.design_matrials.text.SearchBarAndCart
import com.ug.route.ui.design_matrials.text.SmallLogo
import com.ug.route.ui.design_matrials.text.SubcategoryItem
import com.ug.route.ui.no_internet_screen.NoInternetContent
import com.ug.route.ui.theme.DarkPurple
import com.ug.route.ui.theme.Gray80
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
                    {
                        SharedPreferences.selectedCategory = it.name
                        viewModel.onCategoryChange(it)
                    },
                    viewModel::onInternetError
                )
            },
            onClickSubcategory = { subCategoryId ->
                handelInternetError(
                    context,
                    {navController.navigate("${Screen.ProductsScreen.route}/${subCategoryId}")},
                    {navController.navigate(Screen.NoInternetScreen.route)}
                )
            },
            categories = FakeData.categories,
            subCategories = FakeData.subCategories
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
    onCategoryChange : (Category) -> Unit,
    onClickSubcategory: (String) -> Unit,
    categories: List<Category>,
    subCategories: List<SubCategory>
){

    val ids = categories.map { it.id }
    val selectedSubCategories = mutableMapOf<String, List<SubCategory>>()
    for (id in ids) {
        selectedSubCategories[id!!] = subCategories.filter { it.category == id }
    }

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
            subcategoriesGrid,
            noItemsText) = createRefs()

        SideEffect {

            systemUiController.setStatusBarColor(Color.White, darkIcons = true)
        }


        CategoriesSelection(
            modifier = Modifier.constrainAs(categoriesSelection){
                top.linkTo(searchBar.bottom)
            },
            firstIndex = if (SharedPreferences.selectedCategory == null) "Music" else SharedPreferences.selectedCategory!!,
            onCategoryChange = onCategoryChange,
            categories = screenState.categories
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
            text = screenState.selectedCategory.name!!,
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
            title = screenState.selectedCategory.name,
            imageResource = FakeData.categoryImages[screenState.selectedCategory.name]!!,
            modifier = Modifier
                .width(250.dp)
                .padding(horizontal = 16.dp)
                .constrainAs(categoryBanner) {
                    top.linkTo(selectedCategoryText.bottom, 16.dp)
                    start.linkTo(categoriesSelection.end)
                    end.linkTo(parent.end)
                }
        )


        if (selectedSubCategories[screenState.selectedCategory.id]!!.isEmpty()){

            Text(
                modifier = Modifier
                    .width(172.dp)
                    .constrainAs(noItemsText) {
                    top.linkTo(categoryBanner.bottom,172.dp)
                    start.linkTo(categoriesSelection.end)
                    end.linkTo(parent.end) },
                text = "No available data currently",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(300),
                    color = Gray80,
                    textAlign = TextAlign.Center,
                )
            )

        } else {

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .constrainAs(subcategoriesGrid) {
                        top.linkTo(categoryBanner.bottom, 16.dp)
                        start.linkTo(categoriesSelection.end)
                        end.linkTo(parent.end)
                    }
                    .padding(start = 80.dp,
                        end = 80.dp, bottom = 400.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(
                    selectedSubCategories[screenState.selectedCategory.id]!!
                ) { subcategory ->
                    SubcategoryItem(
                        name = subcategory.name ?: "",
                        imageResource = FakeData.subCategoryImages[subcategory.id]!!,
                        onClickItem = {
                            onClickSubcategory(subcategory.id!!)
                        }
                    )
                }
            }

        }
    }
}
