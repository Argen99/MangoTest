package com.example.presentation.ui.navigation.nav_host

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.presentation.ui.navigation.destination.ChatScreenDestination
import com.example.presentation.ui.navigation.destination.ChatsScreenDestination
import com.example.presentation.ui.navigation.destination.EditProfileDestination
import com.example.presentation.ui.navigation.destination.ProfileDestination
import com.example.presentation.ui.screen.chats.ChatsScreen
import com.example.presentation.ui.screen.chats.chat.ChatScreen
import com.example.presentation.ui.screen.profile.ProfileScreen
import com.example.presentation.ui.screen.profile.edit_profile.EditProfileScreen

@Composable
fun MainNavHost(navController: NavHostController, modifier: Modifier) {

    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = ChatsScreenDestination
    ) {
        composable<ChatsScreenDestination> {
            ChatsScreen {
                navController.navigate(ChatScreenDestination(it))
            }
        }
        composable<ProfileDestination> {
            ProfileScreen {
                navController.navigate(EditProfileDestination)
            }
        }
        composable<EditProfileDestination> {
            EditProfileScreen()
        }
        composable<ChatScreenDestination> { backStackEntry->
            val data = backStackEntry.toRoute<ChatScreenDestination>()
            ChatScreen(data.id)
        }
    }
}