package com.fuad.sinus.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fuad.sinus.data.repository.DiagnoseRepository
import com.fuad.sinus.data.repository.WeatherRepository
import com.fuad.sinus.ui.diagnose.DiagnoseViewModel

class ViewModelFactory(
    private val weatherRepository: WeatherRepository?,
    private val diagnoseRepository: DiagnoseRepository?
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)){
            return weatherRepository?.let { WeatherViewModel(it) } as T
        }
        if (modelClass.isAssignableFrom(DiagnoseViewModel::class.java)){
            return diagnoseRepository?.let { DiagnoseViewModel(it) } as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}