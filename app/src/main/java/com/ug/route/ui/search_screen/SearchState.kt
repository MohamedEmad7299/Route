package com.ug.route.ui.search_screen

import com.ug.route.data.fake.FakeData

data class SearchState(
    val query: String,
    val isSearchBarActive: Boolean,
){
    fun matchSearchQuery() : List<String>{
        return FakeData.products.filter{
            it.contains(query,true)
        }
    }
}