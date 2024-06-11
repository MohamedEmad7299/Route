package com.ug.route.ui.cart_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ug.route.data.fake.FakeData
import com.ug.route.data.repositories.Repository
import com.ug.route.networking.ProductCount
import com.ug.route.networking.dto_models.cart_items.CartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _screenState = MutableStateFlow(

        CartState(
            cartItems = emptyList(),
            message = "",
            launchedEffectKey = false,
            isSearchBarActive = true,
            focused = true,
            isLoadingButton = false,
            isLoadingScreen = false,
            totalPrice = 0
        )
    )

    val screenState = _screenState.asStateFlow()

    init {

        getCartItems()
    }

    fun deleteCartItem(product: CartItem){

        val productID = product.product?.id!!
        _screenState.update { prevState ->
            prevState.copy(cartItems = prevState.cartItems.filterNot  { it.product?.id == productID } , totalPrice = prevState.totalPrice-(product.price!!*product.count!!))
        }
        viewModelScope.launch{

            try {

                withTimeout(5000L) {
                    repository.deleteCartItem(productID)
                }

            } catch (e: TimeoutCancellationException) {
                _screenState.update { it.copy(message = "Request timed out") }
            } catch (e: Exception) {
                _screenState.update { it.copy(message = "An error occurred") }
            } finally {
                _screenState.update { prevState ->
                    prevState.copy(launchedEffectKey = true)
                }
            }
        }
    }

    private fun getCartItems(){

        viewModelScope.launch {

            try {

                _screenState.update { prevState ->
                    prevState.copy(isLoadingScreen = true)
                }

                val response = withTimeout(5000L) {
                    repository.getCartItems()
                }

                if (response.isSuccessful){

                    val items = response.body()!!.data!!.cartItems!!
                    FakeData.cartItems = items
                    _screenState.update { it.copy(cartItems = items, totalPrice = items.sumOf { cartItem ->
                        cartItem.price!!*cartItem.count!! })
                    }
                }

            } catch (e: TimeoutCancellationException) {
                _screenState.update { prevState ->
                    prevState.copy(message = "Request timed out")
                }
            } finally {
                _screenState.update { prevState ->
                    prevState.copy(isLoadingScreen = false)
                }
            }
        }
    }

    fun updateCartItem(itemId:String, productCount: ProductCount){

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

    fun clearCart(){

        _screenState.update { prevState ->
            prevState.copy(cartItems = emptyList())
        }

        viewModelScope.launch {

            try {

                _screenState.update { prevState ->
                    prevState.copy(isLoadingButton = true)
                }

                val response = withTimeout(5000L) {
                    repository.clearCart()
                }

                _screenState.update { prevState ->

                    if (response.isSuccessful){

                        prevState.copy(message = "Done")

                    } else {
                        prevState.copy(message = "An error occurred")
                    }
                }
            } catch (e: TimeoutCancellationException) {
                _screenState.update { prevState ->
                    prevState.copy(message = "Request timed out")
                }
            } catch (e: Exception) {
                _screenState.update { prevState ->
                    prevState.copy(message = "An error occurred")
                }
            } finally {
                _screenState.update { prevState ->
                    prevState.copy(isLoadingButton = false)
                }
            }
        }
    }

    fun updateTotalPrice(newValue: Int){

        _screenState.update {
            it.copy(totalPrice = newValue)
        }
    }

    // to refresh the home screen and make it recognize that there is no internet connection
    fun onInternetError(){
        _screenState.update { it.copy(
            launchedEffectKey = !_screenState.value.launchedEffectKey) }
    }
}
