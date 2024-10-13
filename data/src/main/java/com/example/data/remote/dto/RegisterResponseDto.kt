package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.model.RegisterResponse
import com.google.gson.annotations.SerializedName

data class RegisterResponseDto(
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("user_id")
    val userId: Int,
): DataMapper<RegisterResponse> {

    override fun toModel() = RegisterResponse(
        refreshToken = refreshToken,
        accessToken = accessToken,
        userId = userId
    )
}