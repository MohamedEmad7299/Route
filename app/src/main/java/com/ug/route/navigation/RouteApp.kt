//package com.ug.route.navigation
//
//
//import android.annotation.SuppressLint
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavType
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.navArgument
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun RouteApp(){
//
//    Scaffold {
//        val navController = rememberNavController()
//        NavHost(
//            navController = navController,
//            startDestination = "splash_screen"
//        ){
//            composable("splash_screen"){ FirstScreen(navController) }
//            composable(
//                "secondScreen/{name}",
//                arguments = listOf(
//                    navArgument("name"){NavType.StringType}
//                )
//            ){ SecondScreen(navController = navController) }
//        }
//    }
//}