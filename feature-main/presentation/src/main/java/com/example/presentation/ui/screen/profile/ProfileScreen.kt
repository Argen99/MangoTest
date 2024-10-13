package com.example.presentation.ui.screen.profile

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.core_ui.composable.rememberFlowWithLifecycle
import com.example.core_ui.extensions.showToast
import com.example.core_ui.R
import com.example.presentation.ui.screen.profile.ProfileScreenReducer.ProfileEffect
import com.example.presentation.ui.screen.profile.composable.ProfileItem
import com.example.presentation.ui.screen.profile.composable.UserAvatar
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    navigateToEditProfile: () -> Unit
) {
    val context = LocalContext.current
    val viewModel = koinViewModel<ProfileViewModel>()
    val state = viewModel.state.collectAsState()
    val effect = rememberFlowWithLifecycle(viewModel.effect)

    LaunchedEffect(effect) {
        effect.collect { action ->
            when (action) {
                is ProfileEffect.ShowErrorMessage -> {
                    context.showToast(action.message)
                }

                is ProfileEffect.NavigateToEditProfile -> {
                    navigateToEditProfile()
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            UserAvatar(model = state.value.avatar?.toUri())
            AnimatedVisibility(visible = state.value.isLoading) {
                CircularProgressIndicator()
            }
            Spacer(modifier = Modifier.height(32.dp))
            ProfileItem(
                value = state.value.phone,
                label = stringResource(id = R.string.title_phone)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfileItem(
                value = state.value.userName,
                label = stringResource(id = R.string.title_username)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfileItem(value = state.value.city, label = stringResource(id = R.string.title_city))
            Spacer(modifier = Modifier.height(16.dp))
            ProfileItem(
                value = state.value.birthday,
                label = stringResource(id = R.string.title_birthday)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfileItem(
                value = getZodiac(state.value.birthday),
                label = stringResource(id = R.string.title_zodiac)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = {
                viewModel.sendEffect(
                    ProfileEffect.NavigateToEditProfile
                )
            },
        ) {
            Icon(
                painter = rememberVectorPainter(image = Icons.Default.Edit),
                contentDescription = null
            )
        }
    }
}

fun getZodiac(birthDate: String?): String? {
    if (birthDate?.isEmpty() == true) return null
    birthDate?.let { date ->
        val (year, month, day) = date.split("-").map { it.toInt() }
        return when (month) {
            1 -> if (day <= 19) "Козерог" else "Водолей"
            2 -> if (day <= 18) "Водолей" else "Рыбы"
            3 -> if (day <= 20) "Рыбы" else "Овен"
            4 -> if (day <= 19) "Овен" else "Телец"
            5 -> if (day <= 20) "Телец" else "Близнецы"
            6 -> if (day <= 20) "Близнецы" else "Рак"
            7 -> if (day <= 22) "Рак" else "Лев"
            8 -> if (day <= 22) "Лев" else "Дева"
            9 -> if (day <= 22) "Дева" else "Весы"
            10 -> if (day <= 22) "Весы" else "Скорпион"
            11 -> if (day <= 21) "Скорпион" else "Стрелец"
            12 -> if (day <= 21) "Стрелец" else "Козерог"
            else -> "Неизвестный знак"
        }
    } ?: return null
}