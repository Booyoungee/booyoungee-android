package com.eoyeongbooyeong.booyoungee.di

import com.eoyeongbooyeong.booyoungee.di.qualifier.NoToken
import com.eoyeongbooyeong.data.service.AuthService
import com.eoyeongbooyeong.data.service.TokenReissueService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideTokenReissueService(@NoToken retrofit: Retrofit): TokenReissueService =
        retrofit.create(TokenReissueService::class.java)

    @Provides
    @Singleton
    fun provideAuthService(@NoToken retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)
}
