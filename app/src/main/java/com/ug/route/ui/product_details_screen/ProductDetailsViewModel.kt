package com.ug.route.ui.product_details_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ug.route.data.fake.FakeData
import com.ug.route.data.repositories.Repository
import com.ug.route.networking.ProductCount
import com.ug.route.networking.dto_models.cart_items.ProductId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject


@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){


    private val _screenState = MutableStateFlow(

        ProductDetailsState(
            productId = checkNotNull(savedStateHandle["id"]),
            message = "",
            launchedEffectKey = false,
            counter = 1,
            isFavourite = FakeData.wishList.any{ it?.id == checkNotNull(savedStateHandle["id"])}
        )
    )

    val screenState = _screenState.asStateFlow()


    fun onClickPlus(){

        _screenState.update { it.copy(counter = it.counter+1) }
    }

    fun onClickMinus(){

        _screenState.update { it.copy(counter = it.counter-1) }
    }

    fun onClickFavIcon(){

        _screenState.update { it.copy(isFavourite = !it.isFavourite) }
    }

    fun addProductToWishList(productID: String){

        viewModelScope.launch{

            try {

                val response = withTimeout(5000L) {
                    repository.addProductToWishList(ProductId(productID))
                }

                if (!response.isSuccessful) _screenState.update { it.copy(message = response.message()) }

            } catch (e: TimeoutCancellationException) {
                _screenState.update { it.copy(message = "Request timed out") }
            } catch (e: Exception) {
                _screenState.update { it.copy(message = "An error occurred") }
            }
        }
    }

    fun deleteWishListItem(productID: String){

        FakeData.wishList = FakeData.wishList.filterNot { it?.id!! == productID }

        viewModelScope.launch{

            try {

                withTimeout(5000L) {
                    repository.deleteWishListItem(productID)
                }

            } catch (e: TimeoutCancellationException) {
                _screenState.update { it.copy(message = "Request timed out") }
            } catch (e: Exception) {
                _screenState.update { it.copy(message = "An error occurred") }
            }
        }
    }

    private fun updateCartItem(itemId:String, productCount: ProductCount){

        viewModelScope.launch {

            try {

                withTimeout(5000L) {
                    repository.updateCartItem(itemId = itemId, productCount = productCount)
                }

            } catch (e: TimeoutCancellationException) {
                _screenState.update { prevState ->
                    prevState.copy(message = "Request timed out")
                }
            }
        }
    }
    fun addProductToCart(productID: String , popBack: () -> Unit = {}, productCount: ProductCount){

        viewModelScope.launch{

            try {

                val response = withTimeout(5000L) {
                    repository.addProductToCart(ProductId(productID))
                }

                if (response.isSuccessful)
                    _screenState.update { it.copy(message = "Done") }
                else _screenState.update { it.copy(message = response.message()) }

            } catch (e: TimeoutCancellationException) {
                _screenState.update { it.copy(message = "Request timed out") }
            } catch (e: Exception) {
                _screenState.update { it.copy(message = "An error occurred") }
            }

            updateCartItem(productID,productCount)
            getWishList()
            delay(500)
            popBack()
        }
    }

    private fun getWishList(){

        viewModelScope.launch {

            try {

                val response = withTimeout(5000L) {
                    repository.getWishList()
                }

                if (response.isSuccessful){

                    val items = response.body()?.data!!
                    FakeData.wishList = items
                }

            } catch (e: TimeoutCancellationException) {
                _screenState.update { prevState ->
                    prevState.copy(message = e.message.toString())
                }
            }
        }
    }

    fun onInternetError(){
        _screenState.update { it.copy(
            launchedEffectKey = !_screenState.value.launchedEffectKey) }
    }
}