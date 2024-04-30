package com.ug.route.utils

sealed class Screen(val route: String){

    data object SplashScreen : Screen("splash_screen")
    data object SignInScreen : Screen("signIn_screen")
    data object SignUpScreen : Screen("signUp_screen")
    data object ForgetPasswordScreen : Screen("forget_password_screen")
    data object CodeValidationScreen : Screen("code_validation_screen")
    data object ResetPasswordScreen : Screen("reset_password_screen")
    data object HomeScreen : Screen("home_screen")
    data object CategoriesScreen : Screen("categories_screen")
    data object FavouriteScreen : Screen("favourite_screen")
    data object AccountScreen : Screen("account_screen")
    data object SearchScreen : Screen("search_screen")
    data object NoInternetScreen : Screen("no_internet_screen")
    data object CartScreen : Screen("cart_screen")

    data object ProductsScreen : Screen("products__screen")
    data object ProductDetailsScreen : Screen("product_details_screen")
}
