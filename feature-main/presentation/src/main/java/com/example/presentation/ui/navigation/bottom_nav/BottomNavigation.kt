package com.example.presentation.ui.navigation.bottom_nav

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.core_ui.composable.CustomText
import com.example.presentation.ui.navigation.destination.ChatsScreenDestination
import com.example.presentation.ui.navigation.destination.ProfileDestination

@SuppressLint("RestrictedApi")
@Composable
fun BottomNavigation(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomNavigationItems = listOf(
        BottomNavigationItem("Чаты", ChatsScreenDestination, Icons.AutoMirrored.Default.Send),
        BottomNavigationItem("Профиль", ProfileDestination, Icons.Default.Person)
    )

    NavigationBar {
        bottomNavigationItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = null) },
                label = {
                    CustomText(item.name, fontSize = 14.sp)
                },
                selected = currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } == true,
                onClick = {
                    if (currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } == false) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}