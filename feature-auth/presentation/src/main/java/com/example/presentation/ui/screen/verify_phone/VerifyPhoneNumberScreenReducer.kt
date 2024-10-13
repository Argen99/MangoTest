package com.example.presentation.ui.screen.verify_phone

import com.example.core_ui.base.Reducer

class VerifyPhoneNumberScreenReducer :
    Reducer<VerifyPhoneNumberScreenReducer.VerifyPhoneNumberState, VerifyPhoneNumberScreenReducer.VerifyPhoneNumberEvent, VerifyPhoneNumberScreenReducer.VerifyPhoneNumberEffect> {

    data class VerifyPhoneNumberState(
        val isLoading: Boolean = false,
        val otpLength: Int = 6,
        val otpValues: List<String> = List(otpLength) { "" },
        val isCheckButtonEnabled: Boolean = false
    ) : Reducer.ViewState {

        companion object {
            fun initial() = VerifyPhoneNumberState()
        }
    }

    sealed class VerifyPhoneNumberEvent : Reducer.ViewEvent {
        data class UpdateOtpValue(val index: Int, val value: String) : VerifyPhoneNumberEvent()
        data class UpdateLoading(val isLoading: Boolean) : VerifyPhoneNumberEvent()
    }

    sealed class VerifyPhoneNumberEffect : Reducer.ViewEffect {
        data object NavigateToRegister : VerifyPhoneNumberEffect()
        data class ShowErrorMessage(val message: String) : VerifyPhoneNumberEffect()
    }

    override fun reduce(
        previousState: VerifyPhoneNumberState,
        event: VerifyPhoneNumberEvent
    ): Pair<VerifyPhoneNumberState, VerifyPhoneNumberEffect?> {
        
        return when (event) {
            is VerifyPhoneNumberEvent.UpdateOtpValue -> {
                val newOtpValues = previousState.otpValues.toMutableList()
                newOtpValues[event.index] = event.value
                previousState.copy(
                    otpValues = newOtpValues,
                    isCheckButtonEnabled = newOtpValues.all { it.isNotEmpty() }
                ) to null
            }
            is VerifyPhoneNumberEvent.UpdateLoading -> {
                previousState.copy(
                    isLoading = event.isLoading
                ) to null
            }
        }
    }
}