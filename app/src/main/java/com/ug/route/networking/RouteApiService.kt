package com.ug.route.networking

import com.ug.route.networking.dto_models.CategoriesResponse
import com.ug.route.networking.dto_models.CodeValidationResponse
import com.ug.route.networking.dto_models.ForgetPasswordResponse
import com.ug.route.networking.dto_models.SuccessResponse
import com.ug.route.networking.body_models.ForgetPasswordBody
import com.ug.route.networking.body_models.ResetPasswordBody
import com.ug.route.networking.body_models.UserSignInBody
import com.ug.route.networking.body_models.UserSignUpBody
import com.ug.route.networking.body_models.ValidationCodeBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface RouteApiService {

    @POST("auth/signup")
    suspend fun signUp(
        @Body userSignUpBody: UserSignUpBody
    ) : Response<SuccessResponse>

    @POST("auth/signin")
    suspend fun signIn(
        @Body userSignInBody: UserSignInBody
    ) : Response<SuccessResponse>

    @POST("auth/forgotPasswords")
    suspend fun forgotPassword(
        @Body forgetPasswordBody: ForgetPasswordBody
    ) : Response<ForgetPasswordResponse>

    @POST("auth/verifyResetCode")
    suspend fun verifyResetCode(
        @Body validCodeDTO: ValidationCodeBody
    ) : Response<CodeValidationResponse>

    @PUT("auth/resetPassword")
    suspend fun resetPassword(
        @Body resetPasswordBody: ResetPasswordBody
    ) : Response<SuccessResponse>

    @GET ("categories")
    suspend fun getCategories() : Response<CategoriesResponse>
}