package com.example.presentation.ui.screen.register

import com.example.core_ui.base.Reducer
import com.example.core_ui.utils.Regex

class RegisterScreenReducer :
    Reducer<RegisterScreenReducer.RegisterState, RegisterScreenReducer.RegisterEvent, RegisterScreenReducer.RegisterEffect> {

    data class RegisterState(
        val isLoading: Boolean = false,
        val name: String = "",
        val userName: String = "",
        val isValidName: Boolean? = null,
        val isValidUserName: Boolean? = null,
        val isActiveRegisterButton: Boolean = false,
        ) : Reducer.ViewState

    sealed class RegisterEvent : Reducer.ViewEvent {
        data class UpdateName(val name: String) : RegisterEvent()
        data class UpdateUserName(val userName: String) : RegisterEvent()
        data class UpdateLoading(val isLoading: Boolean) : RegisterEvent()
    }

    sealed class RegisterEffect : Reducer.ViewEffect {
        data object NavigateToMain : RegisterEffect()
        data class ShowErrorMessage(val message: String) : RegisterEffect()
    }

    override fun reduce(
        previousState: RegisterState,
        event: RegisterEvent
    ): Pair<RegisterState, RegisterEffect?> {

        return when (event) {
            is RegisterEvent.UpdateName -> {
                val isValidName = event.name.isNotEmpty()
                val isActiveButton = isValidName && previousState.isValidUserName != null && previousState.isValidUserName
                previousState.copy(
                    name = event.name,
                    isValidName = isValidName,
                    isActiveRegisterButton = isActiveButton
                ) to null
            }
            is RegisterEvent.UpdateUserName -> {
                val isValidUserName = Regex.USER_NAME_REGEX.toRegex().matches(event.userName)
                val isActiveButton = isValidUserName && previousState.isValidName != null && previousState.isValidName
                previousState.copy(
                    userName = event.userName,
                    isValidUserName = isValidUserName,
                    isActiveRegisterButton = isActiveButton
                ) to null
            }
            is RegisterEvent.UpdateLoading -> {
                previousState.copy(
                    isLoading = event.isLoading
                ) to null
            }
        }
    }
}