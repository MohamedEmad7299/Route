package com.ug.route.networking

import com.ug.route.data.fake.FakeData
import com.ug.route.networking.dto_models.CategoriesResponse
import com.ug.route.networking.dto_models.CodeValidationResponse
import com.ug.route.networking.dto_models.ForgetPasswordResponse
import com.ug.route.networking.dto_models.SuccessResponse
import com.ug.route.networking.body_models.ForgetPasswordBody
import com.ug.route.networking.body_models.ResetPasswordBody
import com.ug.route.networking.body_models.UserSignInBody
import com.ug.route.networking.body_models.UserSignUpBody
import com.ug.route.networking.body_models.ValidationCodeBody
import com.ug.route.networking.dto_models.cart_items.AddProductToCartResponse
import com.ug.route.networking.dto_models.cart_items.ClearCartResponse
import com.ug.route.networking.dto_models.cart_items.CartItemsResponse
import com.ug.route.networking.dto_models.cart_items.ProductId
import com.ug.route.networking.dto_models.products.ProductsResponse
import com.ug.route.networking.dto_models.sub_categories.SubCategoriesResponse
import com.ug.route.networking.dto_models.wish_list.AddProductToWishListResponse
import com.ug.route.networking.dto_models.wish_list.DeleteWishListItem
import com.ug.route.networking.dto_models.wish_list.WishListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

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

    @GET ("subcategories")
    suspend fun getSubCategories() : Response<SubCategoriesResponse>

    @GET ("products")
    suspend fun getProducts() : Response<ProductsResponse>

    @POST ("cart")
    suspend fun addProductToCart(
        @Header("token") token: String = FakeData.token,
        @Body productID: ProductId
    ) : Response<AddProductToCartResponse>

    @GET ("cart")
    suspend fun getCartItems(
        @Header("token") token: String = FakeData.token,
    ) : Response<CartItemsResponse>

    @DELETE ("cart")
    suspend fun clearCart(
        @Header("token") token: String = FakeData.token,
    ) : Response<ClearCartResponse>

    @DELETE("cart/{itemId}")
    suspend fun deleteCartItem(
        @Header("token") token: String = FakeData.token,
        @Path("itemId") itemId: String
    ): Response<ClearCartResponse>

    @PUT("cart/{itemId}")
    suspend fun updateCartItem(
        @Header("token") token: String = FakeData.token,
        @Path("itemId") itemId: String,
        @Body productCount: ProductCount
    ): Response<ClearCartResponse>

    @POST ("wishlist")
    suspend fun addProductToWishList(
        @Header("token") token: String = FakeData.token,
        @Body productID: ProductId
    ) : Response<AddProductToWishListResponse>

    @GET ("wishlist")
    suspend fun getWishList(
        @Header("token") token: String = FakeData.token,
    ) : Response<WishListResponse>

    @DELETE("wishlist/{itemId}")
    suspend fun deleteWishlistItem(
        @Header("token") token: String = FakeData.token,
        @Path("itemId") itemId: String
    ): Response<DeleteWishListItem>
}