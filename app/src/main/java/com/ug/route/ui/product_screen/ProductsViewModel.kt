package com.ug.route.ui.product_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ug.route.data.database.entities.CartEntity
import com.ug.route.data.database.entities.FavouriteEntity
import com.ug.route.data.database.entities.ProductEntity
import com.ug.route.data.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){


    private val _screenState = MutableStateFlow(

        ProductsState(
            launchedEffectKey = false,
            productKey = checkNotNull(savedStateHandle["product"]),
            products = emptyList(),
            cartItems = emptyList()
        )
    )

    val screenState = _screenState.asStateFlow()
    init {

        viewModelScope.launch {

            repository.getAllProducts().collect { products ->

                if (products.isEmpty()) repository.insertProducts()
                _screenState.value = _screenState.value.copy(products = products)
            }
        }

        viewModelScope.launch {

            repository.getAllCartItems().collect{ cartItems ->

                _screenState.update { it.copy(cartItems = cartItems) }
            }
        }
    }

    fun addToFavourite(favouriteEntity: FavouriteEntity){

        viewModelScope.launch {
            repository.insertFavouriteProduct(favouriteEntity)
        }
    }

    fun updateProduct(productEntity: ProductEntity){

        viewModelScope.launch {
            repository.updateProduct(productEntity)
        }
    }

    fun deleteFavouriteProduct(favouriteEntity : FavouriteEntity){

        viewModelScope.launch {

            repository.deleteFavouriteProduct(favouriteEntity)
        }
    }

    fun insertCartItem(cartEntity: CartEntity){

        viewModelScope.launch{

            repository.insertCartItem(cartEntity)
        }
    }

    fun getCartItemExists(id: Long): Boolean {
        return screenState.value.cartItems.any { it.id == id }
    }
    private fun updateCartItem(cartEntity: CartEntity){

        viewModelScope.launch {
            repository.updateCartItem(cartEntity)
        }
    }

    fun getCartItemAndUpdateIt(itemId: Long){

        viewModelScope.launch {
            repository.getCartItemById(itemId)
                .first()
                .let {
                    updateCartItem(it.copy(count = it.count+1))
                }
        }
    }

    fun onInternetError(){
        _screenState.update { it.copy(
            launchedEffectKey = !_screenState.value.launchedEffectKey) }
    }
}

