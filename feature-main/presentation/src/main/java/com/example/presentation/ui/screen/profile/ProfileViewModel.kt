package com.example.presentation.ui.screen.profile

import androidx.lifecycle.viewModelScope
import com.example.core_ui.base.BaseViewModel
import com.example.domain.repository.MainRepository
import com.example.presentation.ui.screen.profile.ProfileScreenReducer.ProfileEffect
import com.example.presentation.ui.screen.profile.ProfileScreenReducer.ProfileEvent
import com.example.presentation.ui.screen.profile.ProfileScreenReducer.ProfileState
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: MainRepository
) : BaseViewModel<ProfileState, ProfileEvent, ProfileEffect>(
    ProfileState(),
    ProfileScreenReducer()
) {

    init {
        getLocalUserData()
    }

    private fun getLocalUserData() {
        viewModelScope.launch {
            repository.getLocalUserData().collect { data ->
                if (data != null) {
                    sendEvent(
                        ProfileEvent.SetProfileData(data)
                    )
                } else {
                    getUserProfile()
                }
            }
        }
    }

    private fun getUserProfile() {
        repository.getUserProfile().gatherRequest(
            loading = { isLoading ->
                sendEvent(
                    ProfileEvent.UpdateLoading(isLoading)
                )
            },
            success = { profileData ->
                sendEvent(
                    ProfileEvent.SetProfileData(profileData)
                )
            },
            error = { message ->
                sendEffect(
                    ProfileEffect.ShowErrorMessage(message)
                )
            },
            saveDataIfSuccess = { data ->
                repository.saveUserData(data)
            }
        )
    }
}