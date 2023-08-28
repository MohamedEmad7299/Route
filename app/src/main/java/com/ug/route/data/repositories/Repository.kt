package com.ug.route.data.repositories

import com.ug.route.data.models.CodeValidationResponse
import com.ug.route.data.models.ResetPasswordResponse
import com.ug.route.data.models.SuccessResponse
import com.ug.route.networking.dto_models.UserSignInDTO
import com.ug.route.networking.RouteApiService
import com.ug.route.networking.dto_models.ResetPasswordDTO
import com.ug.route.networking.dto_models.UserSignUpDTO
import com.ug.route.networking.dto_models.ValidationCodeDTO
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor (
    private val routeApiService: RouteApiService
    ){
    suspend fun signIn(userSignInDTO: UserSignInDTO) : Response<SuccessResponse>{
        return routeApiService.signIn(userSignInDTO)
    }
    suspend fun signUp(userSignUpDTO: UserSignUpDTO) : Response<SuccessResponse>{
        return routeApiService.signUp(userSignUpDTO)
    }
    suspend fun resetPassword(resetPasswordDTO: ResetPasswordDTO) : Response<ResetPasswordResponse>{
        return routeApiService.resetPassword(resetPasswordDTO)
    }

    suspend fun codeValidation(validationCodeDTO: ValidationCodeDTO) : Response<CodeValidationResponse>{
        return routeApiService.verifyResetCode(validationCodeDTO)
    }
}