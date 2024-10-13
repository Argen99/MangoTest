package com.example.presentation.ui.navigation.destinations

import kotlinx.serialization.Serializable

@Serializable
data class VerifyPhoneNumberDestination(
    val phone: String
)