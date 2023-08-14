package com.ug.route.data.repositories

import com.ug.route.data.models.SuccessRespone
import com.ug.route.networking.dto_models.UserSignInDTO
import com.ug.route.networking.RouteApiService
import com.ug.route.networking.dto_models.UserSignUpDTO
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor (
    private val routeApiService: RouteApiService
    ){

    suspend fun signIn(userSignInDTO: UserSignInDTO) : Response<SuccessRespone>{
        return routeApiService.signIn(userSignInDTO)
    }

    suspend fun signUp(userSignUpDTO: UserSignUpDTO) : Response<SuccessRespone>{
        return routeApiService.signUp(userSignUpDTO)
    }
}