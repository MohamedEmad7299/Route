package com.ug.route.ui.categories_screen

import androidx.lifecycle.ViewModel
import com.ug.route.data.fake.FakeData
import com.ug.route.networking.dto_models.categories.Category
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
            selectedCategory = if (SharedPreferences.selectedCategory == null) FakeData.categories[0] else FakeData.categories.filter { it.name == SharedPreferences.selectedCategory!!}[0],
            categories = FakeData.categories,
            subCategories = emptyList(),
            isLoading = false,
            message = ""
        )
    )

    val screenState = _screenState.asStateFlow()


    fun onCategoryChange(newCategory : Category){

        SharedPreferences.selectedCategory = newCategory.name
        _screenState.update { it.copy(selectedCategory = newCategory) }
    }

    fun onInternetError(){
        _screenState.update { it.copy(
            launchedEffectKey = !_screenState.value.launchedEffectKey) }
    }
}