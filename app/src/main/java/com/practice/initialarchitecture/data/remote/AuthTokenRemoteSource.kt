package com.practice.initialarchitecture.data.remote

import com.practice.initialarchitecture.data.remote.requests.AuthLoginRequest
import com.practice.initialarchitecture.data.remote.requests.AuthRegistrationRequest
import com.practice.initialarchitecture.data.remote.model.AuthTokenDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AuthTokenRemoteSource {

    suspend fun loginAuthToken(authLoginRequest: AuthLoginRequest): Flow<Response<AuthTokenDto>>

    suspend fun registerAuthToken(authRegistrationRequest: AuthRegistrationRequest): Flow<Response<AuthTokenDto>>
}