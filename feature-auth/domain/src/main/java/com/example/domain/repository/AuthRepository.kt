package com.example.domain.repository

import com.example.common.utils.Either
import com.example.domain.model.CheckAuthCodeBody
import com.example.domain.model.CheckAuthCodeResponse
import com.example.domain.model.RegisterBody
import com.example.domain.model.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun sendAuthCode(phone: String): Flow<Either<String, Unit>>
    fun checkAuthCode(body: CheckAuthCodeBody): Flow<Either<String, CheckAuthCodeResponse>>
    fun register(body: RegisterBody): Flow<Either<String, RegisterResponse>>
}