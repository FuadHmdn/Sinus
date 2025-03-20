package com.fuad.sinus.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fuad.sinus.data.Cuaca
import com.fuad.sinus.data.Rekomendasi
import com.fuad.sinus.data.repository.WeatherRepository
import com.fuad.sinus.ui.rekomendasiSinus
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

    fun getRekomendasi(
        suhu: Int,
        kelembapan: Int,
        weatherDesc: String,
        kecepatanAngin: Double,
        tutupanAwan: Int,
    ): Rekomendasi {
        return rekomendasiSinus(
            suhu,
            kelembapan,
            weatherDesc,
            kecepatanAngin,
            tutupanAwan
        )
    }
}
