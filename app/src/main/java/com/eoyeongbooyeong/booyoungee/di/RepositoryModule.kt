package com.eoyeongbooyeong.booyoungee.di

import com.eoyeongbooyeong.data.repositoryImpl.AuthRepositoryImpl
import com.eoyeongbooyeong.data.repositoryImpl.UserRepositoryImpl
import com.eoyeongbooyeong.domain.repository.AuthRepository
import com.eoyeongbooyeong.domain.repository.UserRepository
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
    abstract fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}
