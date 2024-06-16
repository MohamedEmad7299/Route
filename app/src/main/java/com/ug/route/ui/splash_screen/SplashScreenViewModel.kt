package com.ug.route.ui.splash_screen

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ug.route.data.fake.FakeData
import com.ug.route.data.repositories.Repository
import com.ug.route.networking.dto_models.categories.Category
import com.ug.route.networking.dto_models.products.Product
import com.ug.route.networking.dto_models.sub_categories.SubCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){

    init {
        getCategories()
        getSubCategories()
        getProducts()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun getCategories(){

        viewModelScope.launch {

            val response = repository.getCategories()

            FakeData.categories = response.body()?.data as List<Category>
        }
    }

    private fun getSubCategories() {

        viewModelScope.launch {

            val response = repository.getSubCategories()

            FakeData.subCategories = response.body()?.data as List<SubCategory>
        }
    }

    private fun getProducts() {

        viewModelScope.launch {

            val response = repository.getProducts()

            FakeData.products = response.body()?.data as List<Product>
        }
    }
}