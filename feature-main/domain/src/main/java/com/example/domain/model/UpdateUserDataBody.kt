package com.example.domain.model

data class UpdateUserDataBody(
    val phone: String,
    val name: String,
    val username: String,
    val city: String?,
    val birthday: String?,
    val avatar: Avatar64?
)