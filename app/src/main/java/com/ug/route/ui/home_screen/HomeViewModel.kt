package com.ug.route.ui.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ug.route.data.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
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
            history = mutableListOf(),
            focused = true
        )
    )

    val screenState = _screenState.asStateFlow()


    init {
        getCategories()
    }

    private fun getCategories() {

        viewModelScope.launch {

            try {

                val response = withTimeout(5000L) {
                    repository.getCategories()
                }

                if (response.isSuccessful) {

                    _screenState.update { it.copy(categories = response.body()?.data ?: emptyList()) }

                } else _screenState.update { it.copy(message = "An error occurred") }

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
                    prevState.copy(isLoading = false,launchedEffectKey = !prevState.launchedEffectKey)
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

    fun onSearch(searchInput : String){

        _screenState.update { it.copy(query = "") }
        _screenState.value.history.add(searchInput)
    }

    fun onActiveChange(newActiveState : Boolean){

        _screenState.update { it.copy(isSearchBarActive = newActiveState) }
    }

    fun onInternetError(){
        _screenState.update { it.copy(
            message = "No Internet Connection",
            launchedEffectKey = !_screenState.value.launchedEffectKey) }
    }
}