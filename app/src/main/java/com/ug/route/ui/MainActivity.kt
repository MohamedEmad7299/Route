package com.ug.route.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RouteApp()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier , navController: NavController) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}