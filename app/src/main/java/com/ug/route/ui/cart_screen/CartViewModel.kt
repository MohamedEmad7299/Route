package com.ug.route.ui.cart_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ug.route.data.database.entities.CartEntity
import com.ug.route.data.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
            totalPrice = 0
        )
    )

    val screenState = _screenState.asStateFlow()


    init {

        viewModelScope.launch {

            repository.getAllCartItems().collect { cartItems ->
                _screenState.value = _screenState.value.copy(cartItems = cartItems)
            }
        }
    }

    fun deleteCartItem(cartEntity: CartEntity){

        viewModelScope.launch {

            repository.deleteCartItem(cartEntity)
        }
    }

    fun updateCartItem(cartEntity: CartEntity){

        viewModelScope.launch {

            repository.updateCartItem(cartEntity)
        }
    }

    fun makeTotalPriceZero(){
        _screenState.update { it.copy(totalPrice = 0) }
    }

    fun deleteAllCartItems(){

        viewModelScope.launch {

            repository.deleteAllCartItems()
        }
    }

    // to refresh the home screen and make it recognize that there is no internet connection
    fun onInternetError(){
        _screenState.update { it.copy(
            launchedEffectKey = !_screenState.value.launchedEffectKey) }
    }

}
