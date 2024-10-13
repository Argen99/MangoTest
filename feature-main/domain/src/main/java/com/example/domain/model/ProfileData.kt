package com.example.domain.model

data class ProfileData(
    val name: String,
    val username: String,
    val birthday: String?,
    val city: String?,
    val phone: String,
    val avatarUri: String?,
)