package com.example.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.example.presentation.ui.screen.profile.ProfileViewModel
import com.example.presentation.ui.screen.profile.edit_profile.EditProfileViewModel

val mainModule = module {
    viewModelOf(::ProfileViewModel)
    viewModelOf(::EditProfileViewModel)
}