package com.i11usion.core.di

import com.i11usion.core.data.network.CourseService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
object NetworkModule {

    @ApplicationScope
    @Provides
    fun provideJson(): Json =
        Json {
            ignoreUnknownKeys = true
        }

    @ApplicationScope
    @Provides
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient.Builder().build()

    @ApplicationScope
    @Provides
    fun provideRetrofit(
        okHttp: OkHttpClient,
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/u/0/")
            .client(okHttp)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()

    @ApplicationScope
    @Provides
    fun provideCoursesApi(retrofit: Retrofit): CourseService =
        retrofit.create(CourseService::class.java)
}
