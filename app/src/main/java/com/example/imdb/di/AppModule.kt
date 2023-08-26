package com.example.imdb.di

import android.content.Context
import androidx.room.Room
import com.example.imdb.data.local.ImdbDao
import com.example.imdb.data.local.dao.FavoriteMovieDao
import com.example.imdb.data.local.dao.HomeMovieDao
import com.example.imdb.data.network.SimpleRetro
import com.example.imdb.domain.util.Constants.Companion.BASE_URL
import com.example.imdb.domain.util.Constants.Companion.TOKEN
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun okHttpClient() = OkHttpClient.Builder().addInterceptor {
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

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            ImdbDao::class.java,
            "movie_database"
        ).build()
    @Provides
    fun provideHomeMovie(appDataBase: ImdbDao): HomeMovieDao {
        return appDataBase.HomeMovie()
    }

    @Provides
    fun providesFavoriteMovie(appDataBase: ImdbDao): FavoriteMovieDao {
        return appDataBase.FavoriteMovie()
    }
}