package com.ug.route.ui.home_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
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
import com.ug.route.ui.design_matrials.text.SearchBarAndCart
import com.ug.route.ui.design_matrials.text.SliderBanner
import com.ug.route.ui.design_matrials.text.SmallLogo
import com.ug.route.ui.design_matrials.text.Text18
import com.ug.route.ui.theme.DarkBlue

@Composable
fun HomeScreen(
    navController : NavController,
    viewModel: HomeViewModel = hiltViewModel()
){

    val screenState by viewModel.screenState.collectAsState()

    HomeContent(
        screenState = screenState,
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeContent(
    screenState: HomeState
){

    val systemUiController = rememberSystemUiController()

    LazyColumn(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ){
        item{
            ConstraintLayout{

                SideEffect {
                    systemUiController.setStatusBarColor(Color.White,darkIcons = true)
                }

                val(logo,
                    searchBarAndCart,
                    slider,
                    categoriesText,
                    categoriesLazyRow,
                    viewAllText,
                    homeApplianceText,
                    homeApplianceLazyRow,
                    spacer)= createRefs()

                SmallLogo(
                    modifier = Modifier
                        .constrainAs(logo){
                            start.linkTo(parent.start,16.dp)
                            top.linkTo(parent.top,16.dp)
                        }
                )

                SearchBarAndCart(
                    modifier = Modifier.constrainAs(searchBarAndCart){
                        start.linkTo(parent.start)
                        top.linkTo(logo.top,32.dp)
                    },
                    isError = false,
                    value = "",
                    onValueChange = {}) {
                }

                SliderBanner(
                    modifier = Modifier.constrainAs(slider){
                        top.linkTo(searchBarAndCart.bottom)
                    }
                )

                Text18(
                    modifier = Modifier.constrainAs(categoriesText){
                        start.linkTo(parent.start,16.dp)
                        top.linkTo(slider.bottom,24.dp)
                    },
                    text = "Categories",
                    color = Color(0xFF06004F)
                )

                ClickableText(
                    onClick = {},
                    modifier = Modifier.constrainAs(viewAllText){
                        end.linkTo(parent.end,16.dp)
                        top.linkTo(categoriesText.top)
                        bottom.linkTo(categoriesText.bottom)
                    },
                    text = AnnotatedString("view all"),
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF06004F),
                    )
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
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){

                                AsyncImage(
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                        .clip(CircleShape)
                                        .size(100.dp),
                                    model = category?.image,
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop
                                )

                                Text(
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .width(65.dp)
                                        .height(36.dp),
                                    text = category?.name!!,
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        lineHeight = 18.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF06004F),
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
                    text = "Home Appliance",
                    color = Color(0xFF06004F)
                )


                val homeAppliancePNGs = listOf(
                    R.drawable.ghasala,
                    R.drawable.botegaz,
                    R.drawable.makya
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(homeApplianceLazyRow) {
                            top.linkTo(homeApplianceText.bottom, 16.dp)
                        })
                {

                    items(homeAppliancePNGs){ homeAppliancePNG ->


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