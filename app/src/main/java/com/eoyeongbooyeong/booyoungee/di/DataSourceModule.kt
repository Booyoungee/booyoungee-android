package com.eoyeongbooyeong.booyoungee.di

import com.eoyeongbooyeong.data.datasource.AuthDataSource
import com.eoyeongbooyeong.data.datasourceImpl.AuthDataSourceImpl
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
    abstract fun provideAuthDataSource(loginDataSourceImpl: AuthDataSourceImpl): AuthDataSource
}
