package com.example.data.remote.api_service

import com.example.data.remote.dto.CheckAuthCodeBodyDto
import com.example.data.remote.dto.CheckAuthCodeResponseDto
import com.example.data.remote.dto.RefreshTokenBody
import com.example.data.remote.dto.RegisterBodyDto
import com.example.data.remote.dto.RegisterResponseDto
import com.example.data.remote.dto.SendAuthCodeBodyDto
import com.example.domain.model.RegisterBody
import com.example.domain.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("api/v1/users/send-auth-code/")
    suspend fun sendAuthCode(
        @Body body: SendAuthCodeBodyDto
    )

    @POST("api/v1/users/check-auth-code/")
    suspend fun checkAuthCode(
        @Body body: CheckAuthCodeBodyDto
    ): CheckAuthCodeResponseDto

    @POST("api/v1/users/register/")
    suspend fun register(
        @Body body: RegisterBodyDto
    ): RegisterResponseDto

    @POST("api/v1/users/refresh-token/")
    suspend fun refreshToken(
        @Body body: RefreshTokenBody
    ): Response<RegisterResponseDto>
}