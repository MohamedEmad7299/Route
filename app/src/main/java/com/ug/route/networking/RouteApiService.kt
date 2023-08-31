package com.ug.route.networking

import com.ug.route.data.models.CodeValidationResponse
import com.ug.route.data.models.ForgetPasswordResponse
import com.ug.route.data.models.SuccessResponse
import com.ug.route.networking.dto_models.ForgetPasswordDTO
import com.ug.route.networking.dto_models.ResetPasswordDTO
import com.ug.route.networking.dto_models.UserSignInDTO
import com.ug.route.networking.dto_models.UserSignUpDTO
import com.ug.route.networking.dto_models.ValidationCodeDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface RouteApiService {

    @POST("auth/signup")
    suspend fun signUp(
        @Body userSignUpDTO: UserSignUpDTO
    ) : Response<SuccessResponse>

    @POST("auth/signin")
    suspend fun signIn(
        @Body userSignInDTO: UserSignInDTO
    ) : Response<SuccessResponse>

    @POST("auth/forgotPasswords")
    suspend fun forgotPassword(
        @Body forgetPasswordDTO: ForgetPasswordDTO
    ) : Response<ForgetPasswordResponse>

    @POST("auth/verifyResetCode")
    suspend fun verifyResetCode(
        @Body validCodeDTO: ValidationCodeDTO
    ) : Response<CodeValidationResponse>

    @PUT("auth/resetPassword")
    suspend fun resetPassword(
        @Body resetPasswordDTO: ResetPasswordDTO
    ) : Response<SuccessResponse>
}