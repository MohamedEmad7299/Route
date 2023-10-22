package com.ug.route.ui.home_screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ug.route.R
import com.ug.route.networking.dto_models.HomeApplianceProduct
import com.ug.route.ui.design_matrials.text.HomeApplianceCard
import com.ug.route.ui.design_matrials.text.SearchBarAndCart
import com.ug.route.ui.design_matrials.text.SliderBanner
import com.ug.route.ui.design_matrials.text.SmallLogo
import com.ug.route.ui.design_matrials.text.Text18
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.ui.no_internet_screen.NoInternetContent
import com.ug.route.ui.theme.DarkPurple
import com.ug.route.utils.Screen
import com.ug.route.utils.handelInternetError
import com.ug.route.utils.isInternetConnected

@Composable
fun HomeScreen(
    navController : NavController,
    viewModel: HomeViewModel = hiltViewModel()
){

    val screenState by viewModel.screenState.collectAsState()
    val context = LocalContext.current

    if (isInternetConnected(context)){

        HomeContent(
            screenState = screenState,
            navToSearch = {
                handelInternetError(context,
                    {navController.navigate(Screen.SearchScreen.route)},
                    viewModel::onInternetError)
            },
            onClickCategory = {
                handelInternetError(
                    context,
                    {viewModel.onClickCategory(it,navController)},
                    viewModel::onInternetError
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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeContent(
    screenState: HomeState,
    navToSearch : () -> Unit,
    onClickCategory: (String) -> Unit
){

    val systemUiController = rememberSystemUiController()
    val context = LocalContext.current

    if (screenState.message.isNotEmpty()){
        LaunchedEffect(key1 = screenState.launchedEffectKey){
            Toast.makeText(context, screenState.message, Toast.LENGTH_SHORT).show()
        }
    }

    LazyColumn(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ){
        item{

            ConstraintLayout(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
            ){

                val(logo,
                    searchBar,
                    slider,
                    categoriesText,
                    categoriesLazyRow,
                    homeApplianceText,
                    homeApplianceLazyRow,
                    spacer)= createRefs()

                SideEffect {

                    systemUiController.setStatusBarColor(Color.White,darkIcons = true)
                }


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
                    navToSearch = navToSearch
                )


                SliderBanner(
                    modifier = Modifier.constrainAs(slider){
                        top.linkTo(searchBar.bottom)
                    }
                )

                Text18(
                    modifier = Modifier.constrainAs(categoriesText){
                        start.linkTo(parent.start,16.dp)
                        top.linkTo(slider.bottom,24.dp)
                    },
                    text = stringResource(R.string.categories),
                    color = DarkPurple
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(categoriesLazyRow) {
                            top.linkTo(categoriesText.bottom, 16.dp)
                        })
                {

                    if (screenState.isLoading){

                        items(6){

                            CircularProgressIndicator(
                                modifier = Modifier
                                    .padding(horizontal = 50.dp, vertical = 24.dp)
                                    .size(32.dp),
                                color = DarkBlue,
                                strokeWidth = 5.dp
                            )
                        }

                    } else {

                        items(screenState.categories){ category ->

                            Column(
                                modifier = Modifier.clickable {
                                    onClickCategory(category.name)
                                },
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){

                                AsyncImage(
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                        .clip(CircleShape)
                                        .size(100.dp),
                                    model = category.image,
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop
                                )

                                Text(
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .width(65.dp)
                                        .height(36.dp),
                                    text = category.name,
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        lineHeight = 18.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                        fontWeight = FontWeight(400),
                                        color = DarkPurple,
                                        textAlign = TextAlign.Center,
                                    )
                                )
                            }
                        }
                    }
                }

                Text18(
                    modifier = Modifier.constrainAs(homeApplianceText){
                        start.linkTo(parent.start,16.dp)
                        top.linkTo(categoriesLazyRow.bottom,24.dp)
                    },
                    text = stringResource(R.string.home_appliance),
                    color = DarkPurple
                )


                val homeAppliances = listOf(
                    HomeApplianceProduct(
                        image = R.drawable.ghasala,
                        name = stringResource(R.string.washing_machine),
                        review = stringResource(R.string._4_8),
                        price = stringResource(R.string._9000)
                    ),
                    HomeApplianceProduct(
                        image = R.drawable.botegaz,
                        name = stringResource(R.string.new_cooker),
                        review = stringResource(R.string._4_7),
                        price = stringResource(R.string._8000)
                    ),
                    HomeApplianceProduct(
                        image = R.drawable.makya,
                        name = stringResource(R.string.steam_iron),
                        review = stringResource(R.string._4_8),
                        price = stringResource(R.string._2000)
                    )
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(homeApplianceLazyRow) {
                            top.linkTo(homeApplianceText.bottom, 16.dp)
                        })
                {

                    items(homeAppliances){ homeAppliance ->

                        HomeApplianceCard(product = homeAppliance)
                    }
                }

                Image(
                    modifier = Modifier.constrainAs(spacer){
                        top.linkTo(homeApplianceLazyRow.bottom,48.dp) },
                    painter = painterResource(id = R.drawable.botegaz),
                    contentDescription = "")
            }
        }
    }
}