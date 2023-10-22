package com.ug.route.ui.splash_screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ug.route.R
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.utils.Screen
import com.ug.route.utils.SharedPreferences
import com.ug.route.utils.isInternetConnected
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController : NavController){

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(DarkBlue)
    val context = LocalContext.current

    val scale = remember{

        Animatable(3f)
    }

    LaunchedEffect(key1 = true){

        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )

        delay(750)

        val nextDestination =
            if (SharedPreferences.loggedEmail == null)
                Screen.SignInScreen.route
            else
                Screen.HomeScreen.route

        if (isInternetConnected(context)){

            navController.navigate(nextDestination){
                popUpTo(navController.graph.id){
                    inclusive = true
                }
            }
        }

        else navController.navigate(Screen.NoInternetScreen.route)
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue),
    ) {

        val logo = createRef()

        Image(

            painter = painterResource(id = R.drawable.logo_white),
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = 64.dp)
                .constrainAs(logo) {

                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .scale(scale.value)
        )
    }
}