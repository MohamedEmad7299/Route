package com.ug.route.ui.categories_screen

import androidx.lifecycle.ViewModel
import com.ug.route.utils.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel  @Inject constructor() : ViewModel() {


    private val _screenState = MutableStateFlow(

        CategoriesState(
            launchedEffectKey = false,
            selectedCategory = if (SharedPreferences.selectedCategory == null) "Music" else SharedPreferences.selectedCategory!!
        )
    )

    val screenState = _screenState.asStateFlow()


    fun onCategoryChange(newCategory : String){

        SharedPreferences.selectedCategory = newCategory
        _screenState.update { it.copy(selectedCategory = newCategory) }
    }


    fun onInternetError(){
        _screenState.update { it.copy(
            launchedEffectKey = !_screenState.value.launchedEffectKey) }
    }
}