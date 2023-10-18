package com.ug.route.ui.home_screen

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.ug.route.data.repositories.Repository
import com.ug.route.utils.Screen
import com.ug.route.utils.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){

    private val _screenState = MutableStateFlow(

        HomeState(
            query = "",
            categories = emptyList(),
            message = "",
            launchedEffectKey = false,
            isSearchBarActive = true,
            isLoading = true,
            focused = true
        )
    )

    val screenState = _screenState.asStateFlow()


    init {

        viewModelScope.launch {
            repository.refreshCategories()
        }
        getCategories()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun getCategories(){

        viewModelScope.launch {

            try {

                repository.getCategories().collect { categories ->
                    if (categories.isNotEmpty())
                    _screenState.update { it.copy(isLoading = false, launchedEffectKey = !it.launchedEffectKey, categories = categories) }

                }

            } catch (e: Exception) {
                _screenState.update { prevState ->
                    prevState.copy(message = "An error occurred",launchedEffectKey = !prevState.launchedEffectKey)
                }
            }
        }
    }

    fun onQueryChange(newSearchInput : String){

        _screenState.update { it.copy(query = newSearchInput) }
    }

    fun onClickClose(){
        if (_screenState.value.query.isNotEmpty()) _screenState.update { it.copy(query = "") }
    }

    fun onSearch(searchInput: String , navController: NavController){

        if (searchInput in _screenState.value.categories.map { it.name }){

            SharedPreferences.selectedCategory = searchInput

            navController.navigate(Screen.CategoriesScreen.route) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }
    }

    fun onActiveChange(newActiveState : Boolean){

        _screenState.update { it.copy(isSearchBarActive = newActiveState) }
    }

    fun onInternetError(){
        _screenState.update { it.copy(
            launchedEffectKey = !_screenState.value.launchedEffectKey) }
    }
}