package com.ug.route.data.repositories

import com.ug.route.data.database.RouteDatabase
import com.ug.route.data.database.entities.UserEntity
import com.ug.route.networking.ProductCount
import com.ug.route.networking.dto_models.CodeValidationResponse
import com.ug.route.networking.dto_models.ForgetPasswordResponse
import com.ug.route.networking.dto_models.SuccessResponse
import com.ug.route.networking.body_models.UserSignInBody
import com.ug.route.networking.RouteApiService
import com.ug.route.networking.body_models.ForgetPasswordBody
import com.ug.route.networking.body_models.ResetPasswordBody
import com.ug.route.networking.body_models.UserSignUpBody
import com.ug.route.networking.body_models.ValidationCodeBody
import com.ug.route.networking.dto_models.cart_items.AddProductToCartResponse
import com.ug.route.networking.dto_models.CategoriesResponse
import com.ug.route.networking.dto_models.cart_items.ClearCartResponse
import com.ug.route.networking.dto_models.cart_items.CartItemsResponse
import com.ug.route.networking.dto_models.cart_items.ProductId
import com.ug.route.networking.dto_models.products.ProductsResponse
import com.ug.route.networking.dto_models.sub_categories.SubCategoriesResponse
import com.ug.route.networking.dto_models.wish_list.AddProductToWishListResponse
import com.ug.route.networking.dto_models.wish_list.DeleteWishListItem
import com.ug.route.networking.dto_models.wish_list.WishListResponse
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

    suspend fun insertUser(userEntity : UserEntity){
        databaseInstance.userDao().insertUser(userEntity)
    }

    suspend fun updateUser(userEntity : UserEntity){
        databaseInstance.userDao().updateUser(userEntity)
    }

    suspend fun getUserByEmail(email : String) : UserEntity? {
        return databaseInstance.userDao().getUserByEmail(email)
    }

    suspend fun getSubCategories() : Response<SubCategoriesResponse>{
        return routeApiService.getSubCategories()
    }

    suspend fun getProducts() : Response<ProductsResponse>{
        return routeApiService.getProducts()
    }

    suspend fun getCategories(): Response<CategoriesResponse>{
        return routeApiService.getCategories()
    }

    suspend fun addProductToCart(productID: ProductId): Response<AddProductToCartResponse>{
        return routeApiService.addProductToCart(productID = productID)
    }

    suspend fun getCartItems(): Response<CartItemsResponse>{
        return routeApiService.getCartItems()
    }

    suspend fun clearCart(): Response<ClearCartResponse>{
        return routeApiService.clearCart()
    }

    suspend fun deleteCartItem(itemId: String): Response<ClearCartResponse>{
        return routeApiService.deleteCartItem(itemId = itemId)
    }

    suspend fun updateCartItem(itemId: String, productCount: ProductCount): Response<ClearCartResponse>{
        return routeApiService.updateCartItem(itemId = itemId, productCount = productCount)
    }

    suspend fun addProductToWishList(productID: ProductId): Response<AddProductToWishListResponse>{
        return routeApiService.addProductToWishList(productID = productID)
    }

    suspend fun getWishList(): Response<WishListResponse>{
        return routeApiService.getWishList()
    }

    suspend fun deleteWishListItem(itemId: String): Response<DeleteWishListItem>{
        return routeApiService.deleteWishlistItem(itemId = itemId)
    }
}