package com.ug.route.ui.product_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ug.route.data.fake.FakeData
import com.ug.route.data.repositories.Repository
import com.ug.route.networking.dto_models.cart_items.ProductId
import com.ug.route.networking.dto_models.wish_list.WishListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){


    private val _screenState = MutableStateFlow(

        ProductsState(
            launchedEffectKey = false,
            subCategoryId = checkNotNull(savedStateHandle["product"]),
            products = emptyList(),
            message = "",
            isLoading = false
        )
    )

    val screenState = _screenState.asStateFlow()

    private fun getWishList(){

        viewModelScope.launch {

            val response = repository.getWishList()
            FakeData.wishList = (response.body()?.data as MutableList<WishListItem?>?)!!
        }
    }

    fun addProductToCart(productID: String){

        viewModelScope.launch{

            _screenState.update { prevState ->
                prevState.copy(isLoading = true)
            }

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
            } finally {
                _screenState.update { prevState ->
                    prevState.copy(isLoading = false , launchedEffectKey = true)
                }
            }
        }
    }

    fun addProductToWishList(productID: String){

        FakeData.wishList.add(
            WishListItem(
                id = productID
            )
        )

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

            getWishList()
        }

    }

    fun deleteWishListItem(productID: String){

        FakeData.wishList = FakeData.wishList.filterNot { it?.id!! == productID }.toMutableList()

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

            getWishList()
        }
    }

    fun stopLaunchedEffect(){
        _screenState.update { prevState ->
            prevState.copy(message = "")
        }
    }

    fun onInternetError(){
        _screenState.update { it.copy(
            launchedEffectKey = !_screenState.value.launchedEffectKey) }
    }
}

