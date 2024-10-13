package com.example.data.remote.repository

import com.example.common.utils.Either
import com.example.data.local.JwtTokenManager
import com.example.data.remote.api_service.MainApiService
import com.example.data.remote.dto.toDto
import com.example.data.utils.makeNetworkRequest
import com.example.domain.model.ProfileData
import com.example.domain.model.UpdateUserDataBody
import com.example.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(
    private val apiService: MainApiService,
    private val jwtTokenManager: JwtTokenManager
) : MainRepository {

    override fun getAccessToken(): Flow<String?> {
        return jwtTokenManager.getAccessJwt()
    }

    override fun getUserProfile(): Flow<Either<String, ProfileData>> =
        makeNetworkRequest {
            apiService.getUserData().profileData.toModel()
        }

    override fun getLocalUserData(): Flow<ProfileData?> {
        return jwtTokenManager.getUserData()
    }

    override suspend fun saveUserData(data: ProfileData) {
        jwtTokenManager.saveUserData(data)
    }

    override suspend fun updateLocalUserData(data: ProfileData) {
        jwtTokenManager.saveUserData(data)
    }

    override fun updateUserData(data: UpdateUserDataBody): Flow<Either<String, Unit>> =
        makeNetworkRequest {
            apiService.putUserData(data.toDto())
        }
}