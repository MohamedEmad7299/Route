package com.ug.route.ui.design_matrials.text.bottomNav


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.ug.route.ui.theme.DarkBlue

@Composable
fun BottomNavigationBar(
    currentScreen: NavDestination,
    onNavigate: (BottomNavScreen) -> Unit
) {
    val screens = listOf(
        BottomNavScreen.Home,
        BottomNavScreen.Categorise,
        BottomNavScreen.Favourite,
        BottomNavScreen.Account
    )

    Surface(
        shape = RoundedCornerShape(topEnd = 16.dp , topStart = 16.dp),
    ){
        BottomNavigation(
            modifier = Modifier
                .height(60.dp),
            backgroundColor = DarkBlue
        ) {
            screens.forEach { screen ->

                val isSelected = currentScreen.route == screen.route

                BottomNavigationItem(
                    icon = {

                        Box(
                            Modifier
                                .size(48.dp)
                                .background(
                                shape = CircleShape,
                                color = if (isSelected) Color.White else DarkBlue)
                        ){

                            Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp)
                                    .align(Alignment.Center),
                                tint = (if (isSelected) DarkBlue else Color.White)
                            )
                        }
                    },
                    selected = isSelected,
                    onClick = {

                        onNavigate(screen)
                    }
                )
            }
        }
    }
}