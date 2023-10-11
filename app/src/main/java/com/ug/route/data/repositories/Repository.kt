package com.ug.route.data.repositories

import com.ug.route.data.database.UserDatabase
import com.ug.route.data.database.entities.UserData
import com.ug.route.data.models.CategoriesResponse
import com.ug.route.data.models.CodeValidationResponse
import com.ug.route.data.models.ForgetPasswordResponse
import com.ug.route.data.models.SuccessResponse
import com.ug.route.networking.dto_models.UserSignInDTO
import com.ug.route.networking.RouteApiService
import com.ug.route.networking.dto_models.ForgetPasswordDTO
import com.ug.route.networking.dto_models.ResetPasswordDTO
import com.ug.route.networking.dto_models.UserSignUpDTO
import com.ug.route.networking.dto_models.ValidationCodeDTO
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor (
    private val routeApiService: RouteApiService,
    private val databaseInstance : UserDatabase
) {
    suspend fun signIn(userSignInDTO: UserSignInDTO) : Response<SuccessResponse>{
        return routeApiService.signIn(userSignInDTO)
    }
    suspend fun signUp(userSignUpDTO: UserSignUpDTO) : Response<SuccessResponse>{
        return routeApiService.signUp(userSignUpDTO)
    }
    suspend fun forgetPassword(forgetPasswordDTO: ForgetPasswordDTO) : Response<ForgetPasswordResponse>{
        return routeApiService.forgotPassword(forgetPasswordDTO)
    }

    suspend fun codeValidation(validationCodeDTO: ValidationCodeDTO) : Response<CodeValidationResponse>{
        return routeApiService.verifyResetCode(validationCodeDTO)
    }
    suspend fun resetPassword(resetPasswordDTO: ResetPasswordDTO) : Response<SuccessResponse>{
        return routeApiService.resetPassword(resetPasswordDTO)
    }
    suspend fun getCategories() : Response<CategoriesResponse>{
        return routeApiService.getCategories()
    }

    suspend fun insertUser(userData : UserData){
        databaseInstance.userDao().insertUser(userData)
    }

    suspend fun updateUser(userData : UserData){
        databaseInstance.userDao().updateUser(userData)
    }

    suspend fun getUserByEmail(email : String) : UserData? {
        return databaseInstance.userDao().getUserByEmail(email)
    }
}