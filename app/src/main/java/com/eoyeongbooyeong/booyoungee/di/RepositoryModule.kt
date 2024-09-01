package com.eoyeongbooyeong.booyoungee.di

import com.eoyeongbooyeong.data.repositoryImpl.AuthRepositoryImpl
import com.eoyeongbooyeong.data.repositoryImpl.TokenReissueRepositoryImpl
import com.eoyeongbooyeong.domain.repository.AuthRepository
import com.eoyeongbooyeong.domain.repository.TokenReissueRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun provideTokenReissueRepository(
        tokenRepositoryImpl: TokenReissueRepositoryImpl,
    ): TokenReissueRepository
}
