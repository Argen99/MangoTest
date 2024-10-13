package com.example.domain.repository

import com.example.common.utils.Either
import com.example.domain.model.ProfileData
import com.example.domain.model.UpdateUserDataBody
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getAccessToken(): Flow<String?>
    fun getUserProfile(): Flow<Either<String, ProfileData>>
    fun getLocalUserData(): Flow<ProfileData?>
    suspend fun saveUserData(data: ProfileData)
    suspend fun updateLocalUserData(data: ProfileData)
    fun updateUserData(data: UpdateUserDataBody): Flow<Either<String, Unit>>
}