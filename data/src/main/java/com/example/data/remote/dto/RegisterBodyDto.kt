package com.example.data.remote.dto

import com.example.domain.model.RegisterBody

data class RegisterBodyDto(
    val phone: String,
    val name: String,
    val username: String,
)

fun RegisterBody.toDto() = RegisterBodyDto(
    phone = phone,
    name = name,
    username = username
)
