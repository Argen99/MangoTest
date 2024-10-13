package com.example.domain.model

data class CheckAuthCodeResponse(
    val refreshToken: String?,
    val accessToken: String?,
    val userId: Int?,
    val isUserExists: Boolean
)