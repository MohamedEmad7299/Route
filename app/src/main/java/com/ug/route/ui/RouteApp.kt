package com.ug.route.ui


import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ug.route.ui.code_validation_screen.CodeValidationScreen
import com.ug.route.ui.forget_password_screen.ForgetPasswordScreen
import com.ug.route.ui.reset_password_screen.ResetPasswordScreen
import com.ug.route.ui.sign_in_screen.SignInScreen
import com.ug.route.ui.sign_up_screen.SignUpScreen
import com.ug.route.ui.splash_screen.SplashScreen
import com.ug.route.utils.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RouteApp(){

    Scaffold {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.SplashScreen.route
        ){
            composable(Screen.SplashScreen.route){ SplashScreen(navController) }
            composable(Screen.SignInScreen.route){ SignInScreen(navController) }
            composable(Screen.SignUpScreen.route){ SignUpScreen(navController) }
            composable(Screen.ForgetPasswordScreen.route){ ForgetPasswordScreen(navController) }
            composable(
                route = "${Screen.CodeValidationScreen.route}/{email}",
                arguments = listOf(navArgument("email"){NavType.StringType})
            ){ CodeValidationScreen(navController) }
            composable(
                route = "${Screen.ResetPasswordScreen.route}/{email}",
                arguments = listOf(navArgument("email"){NavType.StringType})
            ){ ResetPasswordScreen(navController) }
        }
    }
}