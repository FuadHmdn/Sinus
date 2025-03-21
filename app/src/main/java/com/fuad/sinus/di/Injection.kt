package com.fuad.sinus.di

import android.content.Context
import com.fuad.sinus.data.remote.ApiConfig
import com.fuad.sinus.data.repository.DiagnoseRepository
import com.fuad.sinus.data.repository.WeatherRepository

object Injection {

    fun provideWeatherRepository(context: Context): WeatherRepository {
        val apiService = ApiConfig.getApiService()
        return WeatherRepository.getInstance(apiService)
    }

    fun provideDiagnoseRepository(): DiagnoseRepository {
        return DiagnoseRepository()
    }
}