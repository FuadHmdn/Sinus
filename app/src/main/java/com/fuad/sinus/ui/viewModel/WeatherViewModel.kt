package com.fuad.sinus.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fuad.sinus.data.Cuaca
import com.fuad.sinus.data.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    val cuaca: LiveData<Cuaca?> get() = weatherRepository.cuaca

    fun getCuaca() {
        viewModelScope.launch {
            weatherRepository.fetchCuaca()
        }
    }
}
