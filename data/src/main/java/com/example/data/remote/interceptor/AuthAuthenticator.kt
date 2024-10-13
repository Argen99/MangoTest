package com.example.data.remote.interceptor

import com.example.data.local.JwtTokenManager
import com.example.data.remote.api_service.AuthApiService
import com.example.data.remote.dto.RefreshTokenBody
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AuthAuthenticator(
    private val tokenManager: JwtTokenManager,
    private val authApiService: AuthApiService
) : Authenticator {

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
        private const val MAX_RETRIES = 3
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        if (responseCount(response) >= MAX_RETRIES) {
            return null
        }
        if (response.code == 401) {
            var access = ""
            runBlocking {
                tokenManager.getRefreshJwt()?.let { refresh ->
                    val refreshedRequest = authApiService.refreshToken(
                        RefreshTokenBody(refresh)
                    )

                    refreshedRequest.body()?.let {
                        access = it.accessToken
                        tokenManager.saveAccessJwt(it.accessToken)
                        tokenManager.saveRefreshJwt(it.refreshToken)
                    }
                }
            }
            return response.request.newBuilder()
                .header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $access")
                .build()
        }
        return null
    }

    private fun responseCount(response: Response): Int {
        var result = 1
        var current = response.priorResponse
        while (current != null) {
            result++
            current = current.priorResponse
        }
        return result
    }
}