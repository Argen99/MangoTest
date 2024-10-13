package com.example.data.local

import com.example.domain.model.ProfileData
import kotlinx.coroutines.flow.Flow

interface JwtTokenManager {
    suspend fun saveAccessJwt(token: String)
    suspend fun saveRefreshJwt(token: String)
    fun getAccessJwt(): Flow<String?>
    suspend fun getRefreshJwt(): String?
    suspend fun clearAllTokens()
    fun getUserData(): Flow<ProfileData?>
    suspend fun saveUserData(data: ProfileData)
}