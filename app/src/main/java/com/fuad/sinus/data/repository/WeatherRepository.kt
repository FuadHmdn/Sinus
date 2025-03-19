package com.fuad.sinus.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fuad.sinus.data.Cuaca
import com.fuad.sinus.data.remote.ApiService
import com.fuad.sinus.data.remote.response.CuacaItemItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class WeatherRepository(private val apiService: ApiService) {

    private val _cuaca = MutableLiveData<Cuaca?>()
    val cuaca: LiveData<Cuaca?> get() = _cuaca

    suspend fun fetchCuaca() {
        try {
            val response = apiService.getWeather("18.71.10.1001")
            val cuacaLists = response.data?.get(0)?.cuaca
            if (cuacaLists != null) {
                val allCuacaItems = cuacaLists.filterNotNull().flatMap { it.filterNotNull() }
                val cuacaTerbaru = getRealTimeWeather(allCuacaItems)
                _cuaca.postValue(cuacaTerbaru) // Update LiveData di thread utama

                Log.d("CUACA", cuacaTerbaru?.foto ?: "Damn")
            } else {
                _cuaca.postValue(null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _cuaca.postValue(null)
            Log.d("CUACA", e.message?: "ERROR")
        }
    }

    private fun getRealTimeWeather(cuacaList: List<CuacaItemItem>): Cuaca? {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val nowUtc = ZonedDateTime.now(ZoneOffset.UTC)

        val nextWeather = cuacaList.firstOrNull { item ->
            item.utcDatetime?.let {
                try {
                    val utcTime = LocalDateTime.parse(it, formatter).atZone(ZoneOffset.UTC)
                    utcTime.isAfter(nowUtc)
                } catch (e: Exception) {
                    Log.e("CUACA", "Error parsing date: ${e.message}")
                    false
                }
            } ?: false
        }

        return nextWeather?.let { item ->
            Cuaca(
                suhu = item.t?.toInt() ?: 0,
                kelembapan = item.hu ?: 0,
                cuaca = item.weatherDesc ?: "",
                kecepatanAngin = item.ws?.toInt() ?: 0,
                foto = item.image ?: ""
            )
        }
    }

    companion object {
        @Volatile
        private var instance: WeatherRepository? = null

        fun getInstance(apiService: ApiService): WeatherRepository {
            return instance ?: synchronized(this) {
                instance ?: WeatherRepository(apiService)
            }.also { instance = it }
        }
    }
}
