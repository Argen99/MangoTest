package com.example.presentation.di

import com.example.domain.di.authDomainModule
import com.example.presentation.ui.screen.auth.AuthPhoneNumberViewModel
import com.example.presentation.ui.screen.verify_phone.VerifyPhoneNumberViewModel
import com.example.presentation.ui.screen.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {
    includes(authDomainModule)
    viewModelOf(::AuthPhoneNumberViewModel)
    viewModelOf(::VerifyPhoneNumberViewModel)
    viewModelOf(::RegisterViewModel)
}