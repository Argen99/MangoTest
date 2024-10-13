package com.example.presentation.ui.screen.auth

import com.example.core_ui.base.Reducer

class AuthPhoneNumberScreenReducer :
    Reducer<AuthPhoneNumberScreenReducer.AuthPhoneNumberState, AuthPhoneNumberScreenReducer.AuthPhoneNumberEvent, AuthPhoneNumberScreenReducer.AuthPhoneNumberEffect> {

    data class AuthPhoneNumberState(
        val isLoading: Boolean,
        val phoneNumber: String,
        val isValidPhoneNumber: Boolean,
    ) : Reducer.ViewState {

        companion object {
            fun initial() = AuthPhoneNumberState(
                isLoading = false,
                phoneNumber = "",
                isValidPhoneNumber = false,
            )
        }
    }

    sealed class AuthPhoneNumberEvent : Reducer.ViewEvent {
        data class UpdatePhoneNumber(val phone: String, val isValidPhoneNumber: Boolean) : AuthPhoneNumberEvent()
        data class SendAuthCode(val phone: String) : AuthPhoneNumberEvent()
        data class UpdateLoading(val isLoading: Boolean) : AuthPhoneNumberEvent()
    }

    sealed class AuthPhoneNumberEffect : Reducer.ViewEffect {
        data object NavigateToVerifyPhoneNumber : AuthPhoneNumberEffect()
        data class ShowErrorMessage(val message: String) : AuthPhoneNumberEffect()
    }

    override fun reduce(
        previousState: AuthPhoneNumberState,
        event: AuthPhoneNumberEvent
    ): Pair<AuthPhoneNumberState, AuthPhoneNumberEffect?> {
        
        return when (event) {
            is AuthPhoneNumberEvent.UpdatePhoneNumber -> {
                previousState.copy(
                    phoneNumber = event.phone,
                    isValidPhoneNumber = event.isValidPhoneNumber
                ) to null
            }

            is AuthPhoneNumberEvent.SendAuthCode -> {
                previousState.copy(
                    phoneNumber = event.phone
                ) to AuthPhoneNumberEffect.NavigateToVerifyPhoneNumber
            }

            is AuthPhoneNumberEvent.UpdateLoading -> {
                previousState.copy(
                    isLoading = event.isLoading
                ) to null
            }
        }
    }
}