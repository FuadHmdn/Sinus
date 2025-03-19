package com.fuad.sinus.ui

import com.fuad.sinus.data.Cuaca

sealed class UiState<out T: Any?> {
    object Loading : UiState<Nothing>()

    data class Success<out T: Any>(val data: T?) : UiState<T>()

    data class Error(val errorMessage: String) : UiState<Nothing>()
}