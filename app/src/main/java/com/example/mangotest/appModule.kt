package com.example.mangotest

import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.example.mangotest.MainViewModel
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainViewModel)
}