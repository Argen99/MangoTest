package com.example.presentation.ui.screen.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core_ui.composable.CustomText
import com.example.core_ui.composable.rememberFlowWithLifecycle
import com.example.core_ui.extensions.getDefaultLangCode
import com.example.core_ui.extensions.showToast
import com.example.core_ui.R
import com.example.presentation.ui.navigation.destinations.VerifyPhoneNumberDestination
import com.example.presentation.ui.screen.auth.AuthPhoneNumberScreenReducer.AuthPhoneNumberEffect
import com.togitech.ccp.component.TogiCountryCodePicker
import com.togitech.ccp.data.CountryData
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthPhoneNumberScreen(
    navigateToVerifyPhoneNumber: (VerifyPhoneNumberDestination) -> Unit
) {
    val context = LocalContext.current
    val defaultLang by rememberSaveable {
        mutableStateOf(
            context.getDefaultLangCode(context).uppercase()
        )
    }
    val viewModel: AuthPhoneNumberViewModel = koinViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(viewModel.effect)

    LaunchedEffect(effect) {
        effect.collect { action ->
            when (action) {
                is AuthPhoneNumberEffect.NavigateToVerifyPhoneNumber -> {
                    navigateToVerifyPhoneNumber(VerifyPhoneNumberDestination(state.value.phoneNumber))
                }

                is AuthPhoneNumberEffect.ShowErrorMessage -> {
                    context.showToast(action.message)
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        TogiCountryCodePicker(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            onValueChange = { (code, phone), isValid ->
                viewModel.sendEvent(
                    AuthPhoneNumberScreenReducer.AuthPhoneNumberEvent.UpdatePhoneNumber(
                        phone = code + phone,
                        isValidPhoneNumber = isValid
                    )
                )
            },
            label = { CustomText(stringResource(id = R.string.title_phone)) },
            fallbackCountry = CountryData.isoMap[defaultLang] ?: CountryData.Germany
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(48.dp),
            onClick = {
                viewModel.sendAuthCode(state.value.phoneNumber)
            },
            enabled = state.value.isValidPhoneNumber
        ) {
            if (state.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(32.dp),
                    color = Color.White
                )
            }
            else
                CustomText(text = stringResource(id = R.string.button_send_code), color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}