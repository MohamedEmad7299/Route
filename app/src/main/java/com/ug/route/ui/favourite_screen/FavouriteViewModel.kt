package com.ug.route.ui.favourite_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ug.route.data.fake.FakeData
import com.ug.route.data.repositories.Repository
import com.ug.route.networking.dto_models.cart_items.ProductId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){

    private val _screenState = MutableStateFlow(

        FavouriteState(
            favouriteProducts = FakeData.wishList,
            launchedEffectKey = false,
            isSearchBarActive = true,
            focused = true,
            cartItems = FakeData.cartItems,
            isLoading = false,
            message = ""
        )
    )

    val screenState = _screenState.asStateFlow()

    init {

        getWishList()
    }


    fun deleteWishListItem(itemId: String){

        FakeData.wishList = FakeData.wishList.filterNot { it?.id!! == itemId }

        _screenState.update { prevState ->
            prevState.copy(favouriteProducts = FakeData.wishList)
        }

        viewModelScope.launch{

            try {

                withTimeout(5000L) {
                    repository.deleteWishListItem(itemId)
                }

            } catch (e: TimeoutCancellationException) {
                _screenState.update { it.copy(message = "Request timed out") }
            } catch (e: Exception) {
                _screenState.update { it.copy(message = "An error occurred") }
            }
        }
    }

    private fun getWishList(){

        viewModelScope.launch {

            try {

                _screenState.update { prevState ->
                    prevState.copy(isLoading = true)
                }

                val response = withTimeout(5000L) {
                    repository.getWishList()
                }

                if (response.isSuccessful){

                    val items = response.body()?.data!!
                    FakeData.wishList = items
                    _screenState.update { it.copy(favouriteProducts = items) }
                }

            } catch (e: TimeoutCancellationException) {
                _screenState.update { prevState ->
                    prevState.copy(message = e.message.toString())
                }
            } finally {
                _screenState.update { prevState ->
                    prevState.copy(isLoading = false)
                }
            }
        }
    }

    fun addProductToCart(productID: String){

        viewModelScope.launch{

            try {

                val response = withTimeout(5000L) {
                    repository.addProductToCart(ProductId(productID))
                }

                if (!response.isSuccessful) _screenState.update { it.copy(message = response.message()) }

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

    // to refresh the home screen and make it recognize that there is no internet connection
    fun onInternetError(){
        _screenState.update { it.copy(
            launchedEffectKey = !_screenState.value.launchedEffectKey) }
    }
}