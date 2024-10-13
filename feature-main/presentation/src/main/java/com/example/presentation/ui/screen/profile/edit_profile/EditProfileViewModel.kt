package com.example.presentation.ui.screen.profile.edit_profile

import androidx.lifecycle.viewModelScope
import com.example.core_ui.base.BaseViewModel
import com.example.domain.model.UpdateUserDataBody
import com.example.domain.repository.MainRepository
import com.example.presentation.ui.screen.profile.edit_profile.EditProfileScreenReducer.EditProfileEffect
import com.example.presentation.ui.screen.profile.edit_profile.EditProfileScreenReducer.EditProfileEvent
import com.example.presentation.ui.screen.profile.edit_profile.EditProfileScreenReducer.EditProfileState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val repository: MainRepository
) : BaseViewModel<EditProfileState, EditProfileEvent, EditProfileEffect>(
    EditProfileState(),
    EditProfileScreenReducer()
) {
    init {
        getLocalData()
    }

    private fun getLocalData() {
        viewModelScope.launch {
            repository.getLocalUserData().collectLatest { data ->
                data?.let {
                    sendEvent(
                        EditProfileEvent.SetProfileData(it)
                    )
                }
            }
        }
    }

    fun updateUserData(data: UpdateUserDataBody) {
        repository.updateUserData(data).gatherRequest(
            loading = { isLoading ->
                sendEvent(
                    EditProfileEvent.UpdateLoading(isLoading)
                )
            },
            error = { message ->
                sendEffect(
                    EditProfileEffect.ShowErrorMessage(message)
                )
            },
            saveDataIfSuccess = {
                val currentData = state.value.toProfileData()
                repository.saveUserData(currentData)
            }
        )
    }
}