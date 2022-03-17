package com.digicell.mycash.di

import android.content.Context
import com.digicell.mycash.data.local.AuthTokenDao
import com.digicell.mycash.data.local.AutoAuthPrefsManager
import com.digicell.mycash.data.local.model.AuthTokenEntityMapper
import com.digicell.mycash.data.remote.AuthTokenRemoteSource
import com.digicell.mycash.data.remote.model.AuthTokenDtoMapper
import com.digicell.mycash.repository.AuthRepository
import com.digicell.mycash.repository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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