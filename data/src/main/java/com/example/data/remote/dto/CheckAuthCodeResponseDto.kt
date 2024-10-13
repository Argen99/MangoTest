package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.model.CheckAuthCodeResponse
import com.google.gson.annotations.SerializedName

data class CheckAuthCodeResponseDto(
    @SerializedName("refresh_token")
    val refreshToken: String?,
    @SerializedName("access_token")
    val accessToken: String?,
    @SerializedName("user_id")
    val userId: Int?,
    @SerializedName("is_user_exists")
    val isUserExists: Boolean
): DataMapper<CheckAuthCodeResponse> {

    override fun toModel() = CheckAuthCodeResponse(
        refreshToken = refreshToken,
        accessToken = accessToken,
        userId = userId,
        isUserExists = isUserExists
    )
}