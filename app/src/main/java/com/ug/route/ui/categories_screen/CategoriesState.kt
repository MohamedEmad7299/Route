package com.ug.route.ui.categories_screen

import com.ug.route.networking.dto_models.categories.Category
import com.ug.route.networking.dto_models.sub_categories.SubCategory

data class CategoriesState(

    val launchedEffectKey : Boolean,
    val selectedCategory : Category,
    val categories: List<Category>,
    val subCategories: List<SubCategory>,
    val isLoading: Boolean,
    val message: String
)
