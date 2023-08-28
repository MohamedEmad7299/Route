package com.ug.route.utils

sealed class Screen(val route: String){

    object SplashScreen : Screen("splash_screen")
    object NoInternetScreen : Screen("no_internet_screen")
    object SignInScreen : Screen("signIn_screen")
    object SignUpScreen : Screen("signUp_screen")
    object ResetPasswordScreen : Screen("reset_password_screen")
    object CodeValidationScreen : Screen("code_validation_screen")
}
