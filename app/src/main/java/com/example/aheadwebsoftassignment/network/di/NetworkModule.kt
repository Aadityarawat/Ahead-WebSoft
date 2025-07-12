package com.example.aheadwebsoftassignment.network.di

import com.example.aheadwebsoftassignment.network.ServerAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
private val QUOTES_BASE_URL = "https://demo.socialnetworking.solutions/sesapi/"

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(QUOTES_BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun providesQuotesApi(retrofit: Retrofit) : ServerAPI{
        return retrofit.create(ServerAPI::class.java)
    }
}