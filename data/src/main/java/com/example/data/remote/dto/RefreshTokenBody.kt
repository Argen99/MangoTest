package com.example.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RefreshTokenBody(
    @SerializedName("refresh_token")
    val refreshToken: String
)