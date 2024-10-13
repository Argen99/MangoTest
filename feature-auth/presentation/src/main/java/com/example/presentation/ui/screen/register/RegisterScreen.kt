package com.example.presentation.ui.screen.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.composable.CustomText
import com.example.domain.model.RegisterBody
import com.example.core_ui.R
import com.example.presentation.ui.navigation.destinations.RegisterDestination
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    arguments: RegisterDestination
) {
    val viewModel: RegisterViewModel = koinViewModel()
    val state = viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        CustomText(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = stringResource(id = R.string.register),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = ShapeDefaults.Medium,
            value = arguments.phone,
            onValueChange = { },
            enabled = false,
            colors = OutlinedTextFieldDefaults.colors(disabledTextColor = Color.Gray)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = ShapeDefaults.Medium,
            value = state.value.name,
            onValueChange = { name ->
                viewModel.sendEvent(
                    RegisterScreenReducer.RegisterEvent.UpdateName(name)
                )
            },
            label = {
                CustomText(text = stringResource(id = R.string.title_name))
            },
            isError = state.value.isValidName != null && !state.value.isValidName!!
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = ShapeDefaults.Medium,
            value = state.value.userName,
            onValueChange = { name ->
                viewModel.sendEvent(
                    RegisterScreenReducer.RegisterEvent.UpdateUserName(name)
                )
            },
            label = {
                CustomText(text = stringResource(id = R.string.title_username))
            },
            isError = state.value.isValidUserName != null && !state.value.isValidUserName!!
        )
        if (state.value.isValidUserName != null && !state.value.isValidUserName!!) {
            CustomText(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(id = R.string.hint_username_validation),
                color = Color.Red,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            enabled = state.value.isActiveRegisterButton,
            onClick = {
                viewModel.register(
                    RegisterBody(
                        phone = arguments.phone,
                        name = state.value.name,
                        username = state.value.userName,
                    )
                )
            }
        ) {
            CustomText(text = stringResource(id = R.string.register), color = Color.White)
        }
    }
}