package com.example.data.remote.repository

import com.example.common.utils.Either
import com.example.data.local.JwtTokenManager
import com.example.data.remote.api_service.AuthApiService
import com.example.data.remote.dto.SendAuthCodeBodyDto
import com.example.data.remote.dto.toDto
import com.example.data.utils.makeNetworkRequest
import com.example.domain.model.CheckAuthCodeBody
import com.example.domain.model.CheckAuthCodeResponse
import com.example.domain.model.RegisterBody
import com.example.domain.model.RegisterResponse
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val tokenManager: JwtTokenManager
) : AuthRepository {

    override fun sendAuthCode(phone: String): Flow<Either<String, Unit>> =
        makeNetworkRequest {
            authApiService.sendAuthCode(SendAuthCodeBodyDto(phone))
        }

    override fun checkAuthCode(body: CheckAuthCodeBody): Flow<Either<String, CheckAuthCodeResponse>> =
        makeNetworkRequest {
            authApiService.checkAuthCode(body.toDto()).toModel().also {
                if (it.isUserExists) {
                    tokenManager.saveAccessJwt(it.accessToken!!)
                    tokenManager.saveRefreshJwt(it.refreshToken!!)
                }
            }
        }

    override fun register(body: RegisterBody): Flow<Either<String, RegisterResponse>> =
        makeNetworkRequest {
            authApiService.register(body.toDto()).toModel().also {
                tokenManager.saveAccessJwt(it.accessToken)
                tokenManager.saveRefreshJwt(it.refreshToken)
            }
        }
}