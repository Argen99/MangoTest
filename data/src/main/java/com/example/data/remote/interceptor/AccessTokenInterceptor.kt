package com.example.data.remote.interceptor

import com.example.data.local.JwtTokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AccessTokenInterceptor(
    private val tokenManager: JwtTokenManager,
) : Interceptor {
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenManager.getAccessJwt().first()
        }
        val request = chain.request().newBuilder()
        request.addHeader(name = HEADER_AUTHORIZATION, value ="$TOKEN_TYPE $token")
        return chain.proceed(request.build())
    }
}