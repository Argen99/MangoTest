package com.example.domain.model

data class RegisterResponse(
    val refreshToken: String,
    val accessToken: String,
    val userId: Int,
)