package com.ug.route.ui.home_screen

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.ug.route.data.fake.FakeData
import com.ug.route.data.repositories.Repository
import com.ug.route.utils.Screen
import com.ug.route.utils.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){

    private val _screenState = MutableStateFlow(

        HomeState(
            categories = FakeData.categories,
            message = "",
            launchedEffectKey = false,
            isSearchBarActive = true,
            isLoading = false,
            focused = true
        )
    )

    val screenState = _screenState.asStateFlow()

    fun onClickCategory(searchInput: String, navController: NavController){

        if (searchInput in _screenState.value.categories.map { it.name }){

            SharedPreferences.selectedCategory = searchInput
            navController.navigate(Screen.CategoriesScreen.route)
        }
    }


    // to refresh the home screen and make it recognize that there is no internet connection
    fun onInternetError(){
        _screenState.update { it.copy(
            launchedEffectKey = !_screenState.value.launchedEffectKey) }
    }
}