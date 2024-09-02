package com.eoyeongbooyeong.booyoungee.di

import com.eoyeongbooyeong.data.datasource.AuthDataSource
import com.eoyeongbooyeong.data.datasource.UserDataSource
import com.eoyeongbooyeong.data.datasourceImpl.AuthDataSourceImpl
import com.eoyeongbooyeong.data.datasourceImpl.UserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun provideAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    abstract fun provideUserDataSource(userDataSourceImpl: UserDataSourceImpl): UserDataSource
}
