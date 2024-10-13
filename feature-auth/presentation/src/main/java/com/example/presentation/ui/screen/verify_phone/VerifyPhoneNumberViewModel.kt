package com.example.presentation.ui.screen.verify_phone

import com.example.core_ui.base.BaseViewModel
import com.example.domain.model.CheckAuthCodeBody
import com.example.domain.repository.AuthRepository
import com.example.presentation.ui.screen.verify_phone.VerifyPhoneNumberScreenReducer.VerifyPhoneNumberEffect
import com.example.presentation.ui.screen.verify_phone.VerifyPhoneNumberScreenReducer.VerifyPhoneNumberEvent
import com.example.presentation.ui.screen.verify_phone.VerifyPhoneNumberScreenReducer.VerifyPhoneNumberState

class VerifyPhoneNumberViewModel(
    private val repository: AuthRepository
): BaseViewModel<VerifyPhoneNumberState, VerifyPhoneNumberEvent, VerifyPhoneNumberEffect>(
    initialState = VerifyPhoneNumberState.initial(),
    reducer = VerifyPhoneNumberScreenReducer()
) {

    fun checkAuthCode(body: CheckAuthCodeBody) {
        repository.checkAuthCode(body).gatherRequest(
            loading = { isLoading ->
                sendEvent(
                    VerifyPhoneNumberEvent.UpdateLoading(isLoading)
                )
            },
            success = { response ->
                if (!response.isUserExists) {
                    sendEffect(
                        VerifyPhoneNumberEffect.NavigateToRegister
                    )
                }
            },
            error = { message ->
                sendEffect(
                    VerifyPhoneNumberEffect.ShowErrorMessage(message)
                )
            }
        )
    }
}