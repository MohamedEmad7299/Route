package com.ug.route.ui.search_screen

import androidx.lifecycle.ViewModel
import com.ug.route.data.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){


    private val _screenState = MutableStateFlow(

        SearchState(
            query = "",
            isSearchBarActive = true,

        )
    )

    val screenState = _screenState.asStateFlow()


    fun onQueryChange(newSearchInput : String){

        _screenState.update { it.copy(query = newSearchInput) }
    }

    fun onClickClose(){
        if (_screenState.value.query.isNotEmpty()) _screenState.update { it.copy(query = "") }
    }

    fun onActiveChange(newActiveState : Boolean){

        _screenState.update { it.copy(isSearchBarActive = newActiveState) }
    }
}