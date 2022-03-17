package com.digicell.mycash.repository

import com.digicell.mycash.data.remote.requests.AuthLoginRequest
import com.digicell.mycash.data.remote.requests.AuthRegistrationRequest
import com.digicell.mycash.domain.models.AuthToken
import com.digicell.mycash.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun login(authLoginRequest: AuthLoginRequest) : Flow<Resource<AuthToken>>

    suspend fun register(authRegistrationRequest: AuthRegistrationRequest) : Flow<Resource<AuthToken>>

    suspend fun checkPreviousAuthUser() : Flow<Resource<AuthToken>>

}