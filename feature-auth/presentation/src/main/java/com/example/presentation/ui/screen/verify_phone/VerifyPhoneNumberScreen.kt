package com.example.presentation.ui.screen.verify_phone

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core_ui.composable.CustomText
import com.example.core_ui.composable.OTPInputTextFields
import com.example.core_ui.composable.rememberFlowWithLifecycle
import com.example.core_ui.extensions.showToast
import com.example.domain.model.CheckAuthCodeBody
import com.example.core_ui.R
import com.example.presentation.ui.navigation.destinations.VerifyPhoneNumberDestination
import com.example.presentation.ui.screen.verify_phone.VerifyPhoneNumberScreenReducer.VerifyPhoneNumberEffect
import com.example.presentation.ui.screen.verify_phone.VerifyPhoneNumberScreenReducer.VerifyPhoneNumberEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun VerifyPhoneNumberScreen(
    arguments: VerifyPhoneNumberDestination,
    navigateToRegister: () -> Unit
) {
    val context = LocalContext.current
    val viewModel: VerifyPhoneNumberViewModel = koinViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(viewModel.effect)

    LaunchedEffect(effect) {
        effect.collect { action ->
            when (action) {
                is VerifyPhoneNumberEffect.ShowErrorMessage -> {
                    context.showToast(action.message)
                }

                is VerifyPhoneNumberEffect.NavigateToRegister -> {
                    navigateToRegister()
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(32.dp))
        OTPInputTextFields(
            otpLength = state.value.otpLength,
            otpValues = state.value.otpValues,
            onUpdateOtpValuesByIndex = { index, code ->
                viewModel.sendEvent(
                    VerifyPhoneNumberEvent.UpdateOtpValue(index, code)
                )
            },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = {
                viewModel.checkAuthCode(
                    CheckAuthCodeBody(
                        phone = arguments.phone,
                        code = state.value.otpValues.joinToString("")
                    )
                )
            },
            enabled = state.value.isCheckButtonEnabled
        ) {
            if (state.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(32.dp),
                    color = Color.White
                )
            } else {
                CustomText(text = stringResource(id = R.string.button_confirm), color = Color.White)
            }
        }
    }
}