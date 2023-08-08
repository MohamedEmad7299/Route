package com.ug.route.di

import com.ug.route.networking.RouteApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRouteApiService(okHttpClient: OkHttpClient): RouteApiService{

        val baseURL = "https://ecommerce.routemisr.com/api/v1/"

        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RouteApiService::class.java)
    }

    @Provides
    fun providesClient() : OkHttpClient{
        return OkHttpClient.Builder().build()
    }
}
