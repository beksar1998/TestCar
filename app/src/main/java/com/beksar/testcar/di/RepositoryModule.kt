package com.beksar.testcar.di

import com.beksar.testcar.data.repository.GithubUserRepositoryImpl
import com.beksar.testcar.domain.repository.GithubUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsGithubRepository(
        githubRepositoryImpl: GithubUserRepositoryImpl
    ): GithubUserRepository

}