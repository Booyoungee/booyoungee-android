package com.eoyeongbooyeong.booyoungee.di

import com.eoyeongbooyeong.data.repositoryImpl.AuthRepositoryImpl
import com.eoyeongbooyeong.data.repositoryImpl.BookmarkRepositoryImpl
import com.eoyeongbooyeong.data.repositoryImpl.PlaceRepositoryImpl
import com.eoyeongbooyeong.data.repositoryImpl.ReviewRepositoryImpl
import com.eoyeongbooyeong.data.repositoryImpl.PlaceRepositoryImpl
import com.eoyeongbooyeong.data.repositoryImpl.UserRepositoryImpl
import com.eoyeongbooyeong.domain.repository.AuthRepository
import com.eoyeongbooyeong.domain.repository.BookmarkRepository
import com.eoyeongbooyeong.domain.repository.PlaceRepository
import com.eoyeongbooyeong.domain.repository.ReviewRepository
import com.eoyeongbooyeong.domain.repository.PlaceRepository
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
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository

    @Binds
    @Singleton
    abstract fun provideReviewRepository(
        reviewRepositoryImpl: ReviewRepositoryImpl,
    ): ReviewRepository

    @Binds
    @Singleton
    abstract fun providePlaceRepository(
        placeRepositoryImpl: PlaceRepositoryImpl,
    ): PlaceRepository

    @Binds
    @Singleton
    abstract fun provideBookmarkRepository(
        bookmarkRepositoryImpl: BookmarkRepositoryImpl,
    ): BookmarkRepository
}
