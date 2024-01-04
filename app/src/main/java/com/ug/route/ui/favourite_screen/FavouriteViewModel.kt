package com.ug.route.ui.favourite_screen

import androidx.lifecycle.ViewModel
import com.ug.route.data.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){

    private val _screenState = MutableStateFlow(

        FavouriteState(
            query = "",
            favouriteProducts = emptyList(),
            message = "",
            launchedEffectKey = false,
            isSearchBarActive = true,
            focused = true
        )
    )

    val screenState = _screenState.asStateFlow()


    // to refresh the home screen and make it recognize that there is no internet connection
    fun onInternetError(){
        _screenState.update { it.copy(
            launchedEffectKey = !_screenState.value.launchedEffectKey) }
    }
}