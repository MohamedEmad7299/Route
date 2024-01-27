package com.ug.route.data.repositories

import com.ug.route.data.database.RouteDatabase
import com.ug.route.data.database.entities.CartEntity
import com.ug.route.data.database.entities.CategoryEntity
import com.ug.route.data.database.entities.FavouriteEntity
import com.ug.route.data.database.entities.UserEntity
import com.ug.route.networking.dto_models.CodeValidationResponse
import com.ug.route.networking.dto_models.ForgetPasswordResponse
import com.ug.route.networking.dto_models.SuccessResponse
import com.ug.route.networking.body_models.UserSignInBody
import com.ug.route.networking.RouteApiService
import com.ug.route.networking.body_models.ForgetPasswordBody
import com.ug.route.networking.body_models.ResetPasswordBody
import com.ug.route.networking.body_models.UserSignUpBody
import com.ug.route.networking.body_models.ValidationCodeBody
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor (
    private val routeApiService: RouteApiService,
    private val databaseInstance : RouteDatabase
) {
    suspend fun signIn(userSignInBody: UserSignInBody) : Response<SuccessResponse>{
        return routeApiService.signIn(userSignInBody)
    }
    suspend fun signUp(userSignUpBody: UserSignUpBody) : Response<SuccessResponse>{
        return routeApiService.signUp(userSignUpBody)
    }
    suspend fun forgetPassword(forgetPasswordBody: ForgetPasswordBody) : Response<ForgetPasswordResponse>{
        return routeApiService.forgotPassword(forgetPasswordBody)
    }

    suspend fun codeValidation(validationCodeBody: ValidationCodeBody) : Response<CodeValidationResponse>{
        return routeApiService.verifyResetCode(validationCodeBody)
    }
    suspend fun resetPassword(resetPasswordBody: ResetPasswordBody) : Response<SuccessResponse>{
        return routeApiService.resetPassword(resetPasswordBody)
    }
    fun getCategories() : Flow<List<CategoryEntity>>{
        return databaseInstance.categoryDao().getCategories()
    }

    suspend fun insertUser(userEntity : UserEntity){
        databaseInstance.userDao().insertUser(userEntity)
    }

    suspend fun updateUser(userEntity : UserEntity){
        databaseInstance.userDao().updateUser(userEntity)
    }

    suspend fun insertFavouriteProduct(favouriteEntity: FavouriteEntity){
        databaseInstance.favouriteDao().insertFavouriteProduct(favouriteEntity)
    }

    suspend fun deleteFavouriteProduct(favouriteEntity: FavouriteEntity){
        databaseInstance.favouriteDao().deleteFavouriteProduct(favouriteEntity)
    }

    fun getAllFavouriteProducts() : Flow<List<FavouriteEntity>> {
        return databaseInstance.favouriteDao().getAllFavouriteProducts()
    }

    suspend fun getUserByEmail(email : String) : UserEntity? {
        return databaseInstance.userDao().getUserByEmail(email)
    }
    suspend fun refreshCategories(){

       val categories = routeApiService.getCategories().body()?.data?.map {
            CategoryEntity(
                name = it?.name ?: "",
                image = it?.image ?: "",
                id = 0
            )
        }

        if (categories != null) {
            databaseInstance.categoryDao().replaceCategories(categories)
        }
    }


    suspend fun insertCartItem(cartEntity: CartEntity){
        databaseInstance.cartDao().insertCartItem(cartEntity)
    }

    suspend fun deleteCartItem(cartEntity: CartEntity){
        databaseInstance.cartDao().deleteCartItem(cartEntity)
    }
    fun getAllCartItems() : Flow<List<CartEntity>> {
        return databaseInstance.cartDao().getAllCartItems()
    }
}