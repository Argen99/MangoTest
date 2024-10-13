package com.example.domain.model

data class CheckAuthCodeBody(
    val phone: String,
    val code: String
)