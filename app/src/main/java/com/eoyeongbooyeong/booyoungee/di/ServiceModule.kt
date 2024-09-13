package com.eoyeongbooyeong.booyoungee.di

import com.eoyeongbooyeong.booyoungee.di.qualifier.JWT
import com.eoyeongbooyeong.booyoungee.di.qualifier.NoToken
import com.eoyeongbooyeong.data.service.AuthService
import com.eoyeongbooyeong.data.service.BookmarkService
import com.eoyeongbooyeong.data.service.PlaceService
import com.eoyeongbooyeong.data.service.ReviewService
import com.eoyeongbooyeong.data.service.TourInfoOpenApiService
import com.eoyeongbooyeong.data.service.UserService
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
    fun provideAuthService(
        @NoToken retrofit: Retrofit,
    ): AuthService = retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideUserService(
        @JWT retrofit: Retrofit,
    ): UserService = retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideReviewService(
        @JWT retrofit: Retrofit,
    ): ReviewService = retrofit.create(ReviewService::class.java)

    @Provides
    @Singleton
    fun providePlaceService(
        @JWT retrofit: Retrofit,
    ): PlaceService = retrofit.create(PlaceService::class.java)

    @Provides
    @Singleton
    fun provideBookmarkService(@JWT retrofit: Retrofit): BookmarkService =
        retrofit.create(BookmarkService::class.java)

    @Provides
    @Singleton
    fun provideTourInfoOpenApiService(@JWT retrofit: Retrofit): TourInfoOpenApiService =
        retrofit.create(TourInfoOpenApiService::class.java)
}
