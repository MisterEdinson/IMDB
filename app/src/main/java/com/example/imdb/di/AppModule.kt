package com.example.imdb.di

import com.example.imdb.data.network.SimpleRetro
import com.example.imdb.domain.util.Constants.Companion.BASE_URL
import com.example.imdb.domain.util.Constants.Companion.TOKEN
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun baseUrl() = BASE_URL
    @Provides
    fun logging() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    @Provides
    fun okHttpClient() = OkHttpClient.Builder().addInterceptor{
        val request = it.request().newBuilder().addHeader("X-API-KEY", TOKEN).build()
        it.proceed(request)
    }.addInterceptor(logging()).build()
    @Provides
    fun gson(): GsonConverterFactory = GsonConverterFactory.create()
    @Provides
    @Singleton
    fun provideRetrofit(): SimpleRetro =
        Retrofit.Builder()
            .baseUrl(baseUrl())
            .addConverterFactory(gson())
            .client(okHttpClient())
            .build()
            .create(SimpleRetro::class.java)
}