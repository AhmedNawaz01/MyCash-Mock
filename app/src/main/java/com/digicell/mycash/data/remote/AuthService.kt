package com.digicell.mycash.data.remote

import com.digicell.mycash.data.remote.requests.AuthLoginRequest
import com.digicell.mycash.data.remote.requests.AuthRegistrationRequest
import com.digicell.mycash.data.remote.model.AuthTokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("api/register")
    suspend fun register(
        @Body registrationRequest: AuthRegistrationRequest
    ): Response<AuthTokenDto>

    @POST("api/login")
    suspend fun login(
        @Body loginRequest: AuthLoginRequest
    ): Response<AuthTokenDto>

}