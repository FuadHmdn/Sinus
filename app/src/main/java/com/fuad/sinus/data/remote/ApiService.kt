package com.fuad.sinus.data.remote

import com.fuad.sinus.data.remote.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("publik/prakiraan-cuaca")
    suspend fun getWeather(
        @Query("adm4") kodeWilayah: String
    ): Response

}