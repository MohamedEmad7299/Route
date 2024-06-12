package com.ug.route.ui.search_screen

import com.ug.route.data.fake.FakeData
import com.ug.route.networking.dto_models.products.Product

data class SearchState(
    val query: String,
    val isSearchBarActive: Boolean,
){
    fun matchSearchQuery() : List<Product>{
        return FakeData.products
            .filter{ it.title!!.contains(query,true) }
    }
}