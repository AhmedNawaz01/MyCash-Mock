package com.practice.initialarchitecture.repository

import com.practice.initialarchitecture.data.remote.requests.AuthLoginRequest
import com.practice.initialarchitecture.data.remote.requests.AuthRegistrationRequest
import com.practice.initialarchitecture.domain.models.AuthToken
import com.practice.initialarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun login(authLoginRequest: AuthLoginRequest) : Flow<Resource<AuthToken>>

    suspend fun register(authRegistrationRequest: AuthRegistrationRequest) : Flow<Resource<AuthToken>>

    suspend fun checkPreviousAuthUser() : Flow<Resource<AuthToken>>

}