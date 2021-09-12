package com.beksar.testcar.di

import com.beksar.testcar.data.remoteSource.GithubUserRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteSourceModule {


    @Singleton
    @Provides
    fun providesGithubRemoteSource(okHttpClient: OkHttpClient): GithubUserRemoteSource {
        return NetworkModule.createRestApiAdapter(okHttpClient, NetworkModule.BASE_URL)
    }

}