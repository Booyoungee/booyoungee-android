package com.eoyeongbooyeong.booyoungee.di

import android.content.Context
import android.content.SharedPreferences
import com.eoyeongbooyeong.data.datastore.BooDataStore
import com.eoyeongbooyeong.data.datastore.BooDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideGoingDataStore(dataStoreImpl: BooDataStoreImpl): BooDataStore =
        dataStoreImpl
}
