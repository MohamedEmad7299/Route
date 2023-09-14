package com.ug.route.ui.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ug.route.data.models.Category
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
            searchInput = "",
            categories = emptyList(),
            message = "",
            launchedEffectKey = false,
            isLoading = false
        )
    )

    val screenState = _screenState.asStateFlow()

    init {
        getCategories()
    }

    private fun getCategories() {

        viewModelScope.launch {

            _screenState.update { prevState ->
                prevState.copy(isLoading = true)
            }

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

    fun onChangeSearchInput(newSearchInput : String){

        _screenState.update { it.copy(searchInput = newSearchInput) }
    }

//    private fun checkInputError(): Boolean {
//        val user = user.value
//        _screenState.update { prevState ->
//            prevState.copy(
//                isPasswordError = user.password.isEmpty(),
//                isEmailError = user.email.isEmpty() || !("@" in user.email && "." in user.email)
//            )
//        }
//        return _screenState.value.isEmailError || _screenState.value.isPasswordError
//    }

//    fun makeMessageEmpty(){
//        _screenState.update { it.copy(message = "") }
//    }

    fun onInternetError(){
        _screenState.update { it.copy(
            message = "No Internet Connection",
            launchedEffectKey = !_screenState.value.launchedEffectKey) }
    }
}