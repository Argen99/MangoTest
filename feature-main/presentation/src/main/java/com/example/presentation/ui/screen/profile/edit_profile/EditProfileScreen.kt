package com.example.presentation.ui.screen.profile.edit_profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Base64
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.core_ui.R
import com.example.core_ui.composable.CustomText
import com.example.core_ui.composable.CustomTextField
import com.example.core_ui.composable.rememberFlowWithLifecycle
import com.example.core_ui.extensions.showToast
import com.example.domain.model.Avatar64
import com.example.presentation.ui.screen.profile.composable.BirthDateInput
import com.example.presentation.ui.screen.profile.composable.UserAvatar
import org.koin.androidx.compose.koinViewModel
import java.io.IOException

@Composable
fun EditProfileScreen() {

    val context = LocalContext.current
    val viewModel = koinViewModel<EditProfileViewModel>()
    val state = viewModel.state.collectAsState()
    val effect = rememberFlowWithLifecycle(viewModel.effect)

    val pickMedia = rememberLauncherForActivityResult(PickVisualMedia()) { uri ->
        if (uri != null) {
            viewModel.sendEvent(
                EditProfileScreenReducer.EditProfileEvent.UpdateImage(
                    uri, Avatar64("avatar", getBase64ForUriAndPossiblyCrash(context, uri))
                )
            )
            val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION
            context.contentResolver.takePersistableUriPermission(uri, takeFlags)
        }
    }

    LaunchedEffect(effect) {
        effect.collect { action ->
            when (action) {
                is EditProfileScreenReducer.EditProfileEffect.ShowErrorMessage -> {
                    context.showToast(action.message)
                }

                is EditProfileScreenReducer.EditProfileEffect.SendUpdateUserRequest -> {
                    viewModel.updateUserData(action.data)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Box {
            UserAvatar(
                modifier = Modifier.padding(16.dp),
                model = state.value.imageUri ?: state.value.avatar
            )
            IconButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd),
                onClick = {
                    pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        }
        AnimatedVisibility(visible = state.value.isLoading) {
            CircularProgressIndicator()
        }
        Spacer(modifier = Modifier.height(32.dp))
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = state.value.phone,
            onValueChange = {},
            enabled = false,
            label = {
                CustomText(text = stringResource(id = R.string.title_phone))
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = state.value.username,
            onValueChange = {},
            enabled = false,
            label = {
                CustomText(text = stringResource(id = R.string.title_username))
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = state.value.city ?: "",
            onValueChange = { city ->
                viewModel.sendEvent(
                    EditProfileScreenReducer.EditProfileEvent.UpdateCity(city)
                )
            },
            label = {
                CustomText(text = stringResource(id = R.string.title_city))
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        BirthDateInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = state.value.birthday ?: "",
            onValueChange = { birthday ->
                viewModel.sendEvent(
                    EditProfileScreenReducer.EditProfileEvent.UpdateBirthday(birthday)
                )
            },
            label = {
                CustomText(text = stringResource(id = R.string.title_birthday))
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = {
                viewModel.sendEffect(
                    EditProfileScreenReducer.EditProfileEffect.SendUpdateUserRequest(
                        state.value.toUpdateDataBody()
                    )
                )
            },
            enabled = state.value.saveButtonIsActive
        ) {
            CustomText(text = stringResource(id = R.string.button_save), color = Color.White)
        }
    }
}

private fun getBase64ForUriAndPossiblyCrash(context: Context, uri: Uri): String {
    try {
        val bytes = context.contentResolver.openInputStream(uri)?.readBytes()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    } catch (error: IOException) {
        error.printStackTrace()
        return ""
    }
}
