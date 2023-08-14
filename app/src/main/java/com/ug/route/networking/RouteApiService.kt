package com.ug.route.networking

import com.ug.route.data.models.ForgetPasswordResponse
import com.ug.route.data.models.SuccessRespone
import com.ug.route.networking.dto_models.UserSignInDTO
import com.ug.route.networking.dto_models.UserSignUpDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RouteApiService {

    @POST("auth/signup")
    suspend fun signUp(
        @Body userSignUpDTO: UserSignUpDTO
    ) : Response<SuccessRespone>

    @POST("auth/signin")
    suspend fun signIn(
        @Body userSignInDTO: UserSignInDTO
    ) : Response<SuccessRespone>

    @POST("auth/forgotPasswords")
    suspend fun forgotPassword(
        @Body email: String
    ) : Response<ForgetPasswordResponse>
}