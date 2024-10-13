package com.example.presentation.ui.screen.profile

import com.example.core_ui.base.Reducer
import com.example.domain.model.ProfileData

class ProfileScreenReducer :
    Reducer<ProfileScreenReducer.ProfileState, ProfileScreenReducer.ProfileEvent, ProfileScreenReducer.ProfileEffect> {

    data class ProfileState(
        val isLoading: Boolean = false,
        val userName: String = "",
        val birthday: String? = "",
        val city: String? = "",
        val avatar: String? = "",
        val phone: String = "",
    ) : Reducer.ViewState

    sealed class ProfileEvent : Reducer.ViewEvent {
        data class UpdateLoading(val isLoading: Boolean) : ProfileEvent()
        data class SetProfileData(val profileData: ProfileData) : ProfileEvent()
    }

    sealed class ProfileEffect : Reducer.ViewEffect {
        data object NavigateToEditProfile : ProfileEffect()
        data class ShowErrorMessage(val message: String) : ProfileEffect()
    }

    override fun reduce(
        previousState: ProfileState,
        event: ProfileEvent
    ): Pair<ProfileState, ProfileEffect?> {

        return when (event) {
            is ProfileEvent.UpdateLoading -> {
                previousState.copy(
                    isLoading = event.isLoading
                ) to null
            }

            is ProfileEvent.SetProfileData -> {
                previousState.copy(
                    userName = event.profileData.username,
                    birthday = event.profileData.birthday,
                    city = event.profileData.city,
                    avatar = event.profileData.avatarUri,
                    phone = event.profileData.phone
                ) to null
            }
        }
    }
}