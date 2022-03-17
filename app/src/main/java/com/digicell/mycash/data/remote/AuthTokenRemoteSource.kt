package com.digicell.mycash.data.remote

import com.digicell.mycash.data.remote.requests.AuthLoginRequest
import com.digicell.mycash.data.remote.requests.AuthRegistrationRequest
import com.digicell.mycash.data.remote.model.AuthTokenDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AuthTokenRemoteSource {

    suspend fun loginAuthToken(authLoginRequest: AuthLoginRequest): Flow<Response<AuthTokenDto>>

    suspend fun registerAuthToken(authRegistrationRequest: AuthRegistrationRequest): Flow<Response<AuthTokenDto>>
}