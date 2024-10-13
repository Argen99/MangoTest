package com.example.data.remote.api_service

import com.example.data.remote.dto.UpdateUserDataBodyDto
import com.example.data.remote.dto.UserProfileResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface MainApiService {

    @GET("api/v1/users/me/")
    suspend fun getUserData(): UserProfileResponseDto

    @PUT("api/v1/users/me/")
    suspend fun putUserData(
        @Body body: UpdateUserDataBodyDto
    )
}
