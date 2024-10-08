package com.eoyeongbooyeong.booyoungee.di

import com.eoyeongbooyeong.data.datasource.AuthDataSource
import com.eoyeongbooyeong.data.datasource.BookmarkDataSource
import com.eoyeongbooyeong.data.datasource.MovieDataSource
import com.eoyeongbooyeong.data.datasource.PlaceDataSource
import com.eoyeongbooyeong.data.datasource.ReviewDataSource
import com.eoyeongbooyeong.data.datasource.StampDataSource
import com.eoyeongbooyeong.data.datasource.TourInfoOpenApiDataSource
import com.eoyeongbooyeong.data.datasource.UserDataSource
import com.eoyeongbooyeong.data.datasourceImpl.AuthDataSourceImpl
import com.eoyeongbooyeong.data.datasourceImpl.BookmarkDataSourceImpl
import com.eoyeongbooyeong.data.datasourceImpl.MovieDataSourceImpl
import com.eoyeongbooyeong.data.datasourceImpl.PlaceDataSourceImpl
import com.eoyeongbooyeong.data.datasourceImpl.ReviewDataSourceImpl
import com.eoyeongbooyeong.data.datasourceImpl.StampDataSourceImpl
import com.eoyeongbooyeong.data.datasourceImpl.TourInfoOpenApiDataSourceImpl
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

    @Binds
    @Singleton
    abstract fun provideReviewDataSource(reviewDataSourceImpl: ReviewDataSourceImpl): ReviewDataSource

    @Binds
    @Singleton
    abstract fun providePlaceDataSource(placeDataSourceImpl: PlaceDataSourceImpl): PlaceDataSource

    @Binds
    @Singleton
    abstract fun provideBookmarkDataSource(bookmarkDataSourceImpl: BookmarkDataSourceImpl): BookmarkDataSource


    @Binds
    @Singleton
    abstract fun provideTourInfoOpenApiDataSource(tourInfoOpenApiDataSourceImpl: TourInfoOpenApiDataSourceImpl): TourInfoOpenApiDataSource

    @Binds
    @Singleton
    abstract fun provideMovieDataSource(movieDataSourceImpl: MovieDataSourceImpl): MovieDataSource

    @Binds
    @Singleton
    abstract fun provideStampDataSource(stampDataSourceImpl: StampDataSourceImpl): StampDataSource
}
