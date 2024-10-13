package com.example.mangotest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.MainRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.seconds

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    val accessToken = repository.getAccessToken()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
            ""
        )
}