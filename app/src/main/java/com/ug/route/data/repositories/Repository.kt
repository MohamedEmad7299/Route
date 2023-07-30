package com.ug.route.data.repositories

import com.ug.route.data.models.User
import com.ug.route.networking.ApiImpl
import retrofit2.Response

class Repository {

    suspend fun signIn(user: User) : Response<User>{
        return ApiImpl.apiService.signIn(user)
    }
}