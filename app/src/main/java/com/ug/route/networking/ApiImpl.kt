package com.ug.route.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiImpl {

    private const val BASE_URL = "https://ecommerce.routemisr.com/api/v1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: RouteApiService = retrofit.create(RouteApiService::class.java)
}