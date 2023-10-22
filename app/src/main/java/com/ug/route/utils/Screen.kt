package com.ug.route.utils

sealed class Screen(val route: String){

    object SplashScreen : Screen("splash_screen")
    object SignInScreen : Screen("signIn_screen")
    object SignUpScreen : Screen("signUp_screen")
    object ForgetPasswordScreen : Screen("forget_password_screen")
    object CodeValidationScreen : Screen("code_validation_screen")
    object ResetPasswordScreen : Screen("reset_password_screen")
    object HomeScreen : Screen("home_screen")
    object CategoriesScreen : Screen("categories_screen")
    object FavouriteScreen : Screen("favourite_screen")
    object AccountScreen : Screen("account_screen")
    object SearchScreen : Screen("search_screen")
    object NoInternetScreen : Screen("no_internet_screen")
}
