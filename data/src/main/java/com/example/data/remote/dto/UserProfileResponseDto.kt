package com.example.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserProfileResponseDto(
    @SerializedName("profile_data")
    val profileData: ProfileDataDto
)