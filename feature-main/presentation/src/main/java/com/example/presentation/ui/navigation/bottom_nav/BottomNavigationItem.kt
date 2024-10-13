package com.example.presentation.ui.navigation.bottom_nav

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem<T : Any>(val name: String, val route: T, val icon: ImageVector)