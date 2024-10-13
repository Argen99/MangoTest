package com.example.mangotest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.mangotest.ui.theme.MangoTestTheme
import com.example.presentation.ui.navigation.AuthScaffold
import com.example.presentation.ui.navigation.MainScaffold
import com.example.presentation.ui.navigation.nav_host.AuthNavHost
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = getViewModel<MainViewModel>()
            val accessToken = viewModel.accessToken.collectAsState()
            MangoTestTheme(darkTheme = false) {

                when {
                    accessToken.value?.isEmpty() == true -> {
                        LoadingScreen()
                    }
                    accessToken.value == null -> {
                        AuthScaffold()
                    }
                    else -> {
                        MainScaffold()
                    }
                }
            }
        }
    }
}

/**
 * Состояние [accessToken] isEmpty используется в качестве начального значения, до вычисления
 * фактического значения
 */
@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}