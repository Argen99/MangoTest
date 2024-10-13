package com.example.presentation.ui.navigation.nav_host

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.presentation.ui.navigation.destinations.LoginDestination
import com.example.presentation.ui.navigation.destinations.RegisterDestination
import com.example.presentation.ui.navigation.destinations.VerifyPhoneNumberDestination
import com.example.presentation.ui.screen.auth.AuthPhoneNumberScreen
import com.example.presentation.ui.screen.register.RegisterScreen
import com.example.presentation.ui.screen.verify_phone.VerifyPhoneNumberScreen

@Composable
fun AuthNavHost(navController: NavHostController, modifier: Modifier) {

    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = LoginDestination
    ) {
        composable<LoginDestination> {
            AuthPhoneNumberScreen { data ->
                navController.navigate(data)
            }
        }
        composable<VerifyPhoneNumberDestination> { backStackEntry ->
            val data = backStackEntry.toRoute<VerifyPhoneNumberDestination>()
            VerifyPhoneNumberScreen(data) {
                navController.navigate(RegisterDestination(data.phone))
            }
        }
        composable<RegisterDestination> { backStackEntry ->
            val data = backStackEntry.toRoute<RegisterDestination>()
            RegisterScreen(data)
        }
    }
}