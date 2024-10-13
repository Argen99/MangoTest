package com.example.presentation.ui.screen.register

import com.example.core_ui.base.BaseViewModel
import com.example.domain.model.RegisterBody
import com.example.domain.repository.AuthRepository
import com.example.presentation.ui.screen.register.RegisterScreenReducer.RegisterState
import com.example.presentation.ui.screen.register.RegisterScreenReducer.RegisterEffect
import com.example.presentation.ui.screen.register.RegisterScreenReducer.RegisterEvent

class RegisterViewModel(
    private val repository: AuthRepository
) : BaseViewModel<RegisterState, RegisterEvent, RegisterEffect>(
    initialState = RegisterState(),
    reducer = RegisterScreenReducer()
) {

    fun register(body: RegisterBody) {
        repository.register(body).gatherRequest(
            loading = { isLoading ->
                sendEvent(
                    RegisterEvent.UpdateLoading(isLoading)
                )
            },
            error = { message ->
                sendEffect(
                    RegisterEffect.ShowErrorMessage(message)
                )
            }
        )
    }
}