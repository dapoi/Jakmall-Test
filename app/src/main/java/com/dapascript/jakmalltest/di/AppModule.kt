package com.dapascript.jakmalltest.di

import androidx.viewbinding.BuildConfig
import com.dapascript.jakmalltest.data.repository.ItemRepository
import com.dapascript.jakmalltest.data.repository.ItemRepositoryImpl
import com.dapascript.jakmalltest.data.source.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val logging =
            if (!BuildConfig.DEBUG) HttpLoggingInterceptor.Level.NONE else HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(logging))
            .build()
        val retrofit = retrofit2.Retrofit.Builder()
            .baseUrl("https://v2.jokeapi.dev/")
            .client(okHttpClient)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): ItemRepository {
        return ItemRepositoryImpl(apiService)
    }
}