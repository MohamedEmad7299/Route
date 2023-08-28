package com.ug.route.ui.unused.no_internet_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class NoInternetViewModel  @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val previousRoute : String = checkNotNull(savedStateHandle["previousRoute"])
}
