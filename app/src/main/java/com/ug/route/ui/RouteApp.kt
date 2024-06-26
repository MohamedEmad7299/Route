package com.ug.route.ui


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ug.route.ui.favourite_screen.FavouriteScreen
import com.ug.route.ui.account_screen.AccountScreen
import com.ug.route.ui.cart_screen.CartScreen
import com.ug.route.ui.categories_screen.CategoriesScreen
import com.ug.route.ui.code_validation_screen.CodeValidationScreen
import com.ug.route.ui.search_screen.SearchScreen
import com.ug.route.ui.design_matrials.text.bottomNav.BottomNavScreen
import com.ug.route.ui.design_matrials.text.bottomNav.BottomNavigationBar
import com.ug.route.ui.forget_password_screen.ForgetPasswordScreen
import com.ug.route.ui.home_screen.HomeScreen
import com.ug.route.ui.no_internet_screen.NoInternetScreen
import com.ug.route.ui.product_details_screen.ProductDetailsScreen
import com.ug.route.ui.product_screen.ProductsScreen
import com.ug.route.ui.reset_password_screen.ResetPasswordScreen
import com.ug.route.ui.sign_in_screen.SignInScreen
import com.ug.route.ui.sign_up_screen.SignUpScreen
import com.ug.route.ui.splash_screen.SplashScreen
import com.ug.route.utils.Screen
import com.ug.route.utils.isInternetConnected

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition",
    "SuspiciousIndentation"
)
@Composable
fun RouteApp(){

    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry.value?.destination
    val context = LocalContext.current

    val bottomBarScreens = listOf(
        BottomNavScreen.Home.route,
        BottomNavScreen.Categorise.route,
        BottomNavScreen.Favourite.route,
        BottomNavScreen.Account.route
    )

    Scaffold(

        bottomBar = {

            if (currentDestination?.route in bottomBarScreens) {

                BottomNavigationBar(
                    currentScreen = currentDestination!!,
                    onNavigate = { screen ->
                        if (isInternetConnected(context)){
                            if (screen.route != currentDestination.route)
                            navController.navigate(screen.route)
                        }
                        else Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    ){

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
            composable(Screen.HomeScreen.route){ HomeScreen(navController) }
            composable(Screen.CategoriesScreen.route) { CategoriesScreen(navController) }
            composable(Screen.FavouriteScreen.route){ FavouriteScreen(navController) }
            composable(Screen.AccountScreen.route){ AccountScreen(navController) }
            composable(Screen.SearchScreen.route){ SearchScreen(navController) }
            composable(Screen.NoInternetScreen.route){ NoInternetScreen(navController) }
            composable(Screen.CartScreen.route){ CartScreen(navController) }
            composable(
                route = "${Screen.ProductsScreen.route}/{product}",
                arguments = listOf(navArgument("product"){NavType.StringType}))
            { ProductsScreen(navController) }
            composable(
                route = "${Screen.ProductDetailsScreen.route}/{id}",
                arguments = listOf(navArgument("id"){NavType.StringType}))
            { ProductDetailsScreen(navController) }
        }
    }
}