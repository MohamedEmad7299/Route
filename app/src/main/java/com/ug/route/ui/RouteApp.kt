package com.ug.route.ui


import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ug.route.ui.reset_password_screen.ForgotPasswordScreen
import com.ug.route.ui.sign_in_screen.SignInScreen
import com.ug.route.ui.sign_up_screen.SignUpScreen
import com.ug.route.ui.splash_screen.SplashScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteApp(){

    Scaffold {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "splash_screen"
        ){
            composable("splash_screen"){ SplashScreen(navController) }
            composable("signIn_screen"){ SignInScreen(navController) }
            composable("signUp_screen"){ SignUpScreen(navController) }
            composable("reset_password_screen"){ ForgotPasswordScreen(navController) }
        }
    }
}