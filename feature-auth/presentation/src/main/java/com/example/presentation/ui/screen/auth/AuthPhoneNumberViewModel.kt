package com.example.presentation.ui.screen.auth

import com.example.core_ui.base.BaseViewModel
import com.example.domain.repository.AuthRepository
import com.example.presentation.ui.screen.auth.AuthPhoneNumberScreenReducer.AuthPhoneNumberEffect
import com.example.presentation.ui.screen.auth.AuthPhoneNumberScreenReducer.AuthPhoneNumberEvent
import com.example.presentation.ui.screen.auth.AuthPhoneNumberScreenReducer.AuthPhoneNumberState

class AuthPhoneNumberViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel<AuthPhoneNumberState, AuthPhoneNumberEvent, AuthPhoneNumberEffect>(
    initialState = AuthPhoneNumberState.initial(),
    reducer = AuthPhoneNumberScreenReducer()
) {
    fun sendAuthCode(phone: String) {
        authRepository.sendAuthCode(phone).gatherRequest(
            loading = { isLoading ->
                sendEvent(
                    AuthPhoneNumberEvent.UpdateLoading(isLoading)
                )
            },
            success = {
                sendEffect(
                    AuthPhoneNumberEffect.NavigateToVerifyPhoneNumber
                )
            },
            error = { message ->
                sendEffect(
                    AuthPhoneNumberEffect.ShowErrorMessage(message)
                )
            }
        )
    }
}