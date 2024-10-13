package com.example.presentation.ui.screen.profile.edit_profile

import android.net.Uri
import com.example.core_ui.base.Reducer
import com.example.domain.model.Avatar64
import com.example.domain.model.ProfileData
import com.example.domain.model.UpdateUserDataBody

class EditProfileScreenReducer :
    Reducer<EditProfileScreenReducer.EditProfileState, EditProfileScreenReducer.EditProfileEvent, EditProfileScreenReducer.EditProfileEffect> {

    data class EditProfileState(
        val isLoading: Boolean = false,
        val name: String = "",
        val username: String = "",
        val birthday: String? = "",
        val city: String? = "",
        val avatar: String? = "",
        val phone: String = "",
        val imageUri: Uri? = null,
        val saveButtonIsActive: Boolean = false,
        val avatar64: Avatar64? = null,
    ) : Reducer.ViewState

    sealed class EditProfileEvent : Reducer.ViewEvent {
        data class UpdateLoading(val isLoading: Boolean) : EditProfileEvent()
        data class SetProfileData(val profileData: ProfileData) : EditProfileEvent()
        data class UpdateCity(val city: String) : EditProfileEvent()
        data class UpdateBirthday(val birthday: String) : EditProfileEvent()
        data class UpdateImage(val imageUri: Uri, val avatar64: Avatar64?) : EditProfileEvent()
    }

    sealed class EditProfileEffect : Reducer.ViewEffect {
        data class ShowErrorMessage(val message: String) : EditProfileEffect()
        data class SendUpdateUserRequest(val data: UpdateUserDataBody) : EditProfileEffect()
    }

    override fun reduce(
        previousState: EditProfileState,
        event: EditProfileEvent
    ): Pair<EditProfileState, EditProfileEffect?> {

        return when (event) {
            is EditProfileEvent.UpdateLoading -> {
                previousState.copy(
                    isLoading = event.isLoading
                ) to null
            }

            is EditProfileEvent.SetProfileData -> {
                previousState.copy(
                    name = event.profileData.name,
                    username = event.profileData.username,
                    birthday = event.profileData.birthday,
                    city = event.profileData.city,
                    avatar = event.profileData.avatarUri,
                    phone = event.profileData.phone
                ) to null
            }

            is EditProfileEvent.UpdateCity -> {
                val newCityValue = event.city
                val oldCityValue = previousState.city
                val uriIsNotEmpty = previousState.imageUri.toString().isNotEmpty()
                val buttonIsActive = newCityValue != oldCityValue || uriIsNotEmpty
                previousState.copy(
                    city = event.city,
                    saveButtonIsActive = buttonIsActive
                ) to null
            }

            is EditProfileEvent.UpdateBirthday -> {
                val newBirthdayValue = event.birthday
                val oldBirthdayValue = previousState.birthday
                val uriIsNotEmpty = previousState.imageUri.toString().isNotEmpty()
                val buttonIsActive = newBirthdayValue != oldBirthdayValue || uriIsNotEmpty
                previousState.copy(
                    birthday = event.birthday,
                    saveButtonIsActive = buttonIsActive
                ) to null
            }

            is EditProfileEvent.UpdateImage -> {
                previousState.copy(
                    imageUri = event.imageUri,
                    avatar64 = event.avatar64,
                    saveButtonIsActive = true
                ) to null
            }
        }
    }
}

fun EditProfileScreenReducer.EditProfileState.toUpdateDataBody() = UpdateUserDataBody(
    phone = phone,
    name = name,
    username = username,
    city = city,
    birthday = birthday,
    avatar = avatar64
)

fun EditProfileScreenReducer.EditProfileState.toProfileData() = ProfileData(
    phone = phone,
    name = name,
    username = username,
    city = city,
    birthday = birthday,
    avatarUri = imageUri?.toString()
)