package com.ug.route.networking

import com.ug.route.data.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RouteApiService {

    @POST("auth/signin")
    suspend fun signIn(
        @Body user: User
    ) : Response<User>


}