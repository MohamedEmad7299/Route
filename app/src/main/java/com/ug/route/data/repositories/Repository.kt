package com.ug.route.data.repositories

import com.ug.route.data.models.User
import com.ug.route.networking.RouteApiService
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor (
    private val routeApiService: RouteApiService
    ){

    suspend fun signIn(user: User) : Response<User>{
        return routeApiService.signIn(user)
    }
}