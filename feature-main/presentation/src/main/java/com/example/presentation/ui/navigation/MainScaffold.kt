package com.example.presentation.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.presentation.ui.navigation.bottom_nav.BottomNavigation
import com.example.presentation.ui.navigation.nav_host.MainNavHost

@Composable
fun MainScaffold() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation(navController)
        }
    ) { innerPadding ->
        MainNavHost(navController, Modifier.padding(innerPadding))
    }
}