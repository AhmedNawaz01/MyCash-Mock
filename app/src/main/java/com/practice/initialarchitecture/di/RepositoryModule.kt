package com.practice.initialarchitecture.di

import android.content.Context
import com.practice.initialarchitecture.data.local.AuthTokenDao
import com.practice.initialarchitecture.data.local.AutoAuthPrefsManager
import com.practice.initialarchitecture.data.local.model.AuthTokenEntityMapper
import com.practice.initialarchitecture.data.remote.AuthTokenRemoteSource
import com.practice.initialarchitecture.data.remote.model.AuthTokenDtoMapper
import com.practice.initialarchitecture.repository.AuthRepository
import com.practice.initialarchitecture.repository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAutoAuthPrefsManager(@ApplicationContext appContext: Context): AutoAuthPrefsManager {
        return AutoAuthPrefsManager(appContext)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(
        authTokenRemoteSource: AuthTokenRemoteSource,
        authTokenDao: AuthTokenDao,
        authTokenDtoMapper: AuthTokenDtoMapper,
        authTokenEntityMapper: AuthTokenEntityMapper,
        autoAuthPrefsManager: AutoAuthPrefsManager
    ): AuthRepository {
        return AuthRepositoryImpl(
            authTokenRemoteSource = authTokenRemoteSource,
            authTokenDao = authTokenDao,
            authTokenDtoMapper = authTokenDtoMapper,
            authTokenEntityMapper = authTokenEntityMapper,
            autoAuthPrefsManager = autoAuthPrefsManager
        )
    }

}