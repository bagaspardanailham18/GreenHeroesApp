package com.bagaspardanailham.greenheroesapp.presentation

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T?) : UiState<T>()
    data class Error(val message: String? = null) : UiState<Nothing>()
    object ErrorConnection : UiState<Nothing>()
    object Empty : UiState<Nothing>()
}