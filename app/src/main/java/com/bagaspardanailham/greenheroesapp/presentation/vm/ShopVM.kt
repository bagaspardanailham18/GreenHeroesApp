package com.bagaspardanailham.greenheroesapp.presentation.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ShopVM : ViewModel() {
    var searchQuery by mutableStateOf("")
        private set


    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }
}