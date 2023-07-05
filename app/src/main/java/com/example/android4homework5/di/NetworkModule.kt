
package com.example.android4homework5.di

import com.example.android4homework5.data.remote.RetrofitClient
import com.example.android4homework5.data.remote.apiservices.CharacterApiService
import com.example.android4homework5.data.remote.apiservices.LocationApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    val retrofitClient = RetrofitClient()

    @Singleton
    @Provides
    fun provideCharacterApiService() : CharacterApiService {
        return retrofitClient.provideCharacterApiService()
    }

    @Singleton
    @Provides
    fun provideLocationApiService() : LocationApiService {
        return retrofitClient.provideLocationApiService()
    }

}