package com.fuad.sinus.ui

import com.fuad.sinus.R
import com.fuad.sinus.data.Rekomendasi

fun rekomendasiSinus(
    suhu: Int,
    kelembapan: Int,
    weatherDesc: String,
    kecepatanAngin: Double,
    tutupanAwan: Int,
): Rekomendasi {
    return when {
        suhu == 0 && kelembapan == 0 && tutupanAwan == 0 && kecepatanAngin == 0.0 && weatherDesc.contains("Cerah", ignoreCase = true) ->
            Rekomendasi(
                rekomendasi = "Memuat data...",
                icon = R.drawable.sinus7
            )

        // Udara lembap berlebihan dan cuaca buruk (memicu sinus)
        weatherDesc.contains("Petir", ignoreCase = true) && kelembapan > 75 ->
            Rekomendasi(
                rekomendasi = "Hindari keluar rumah! Kelembapan tinggi dan cuaca buruk bisa memperparah sinus. Gunakan masker jika terpaksa keluar.",
                icon = R.drawable.sinus2
            )

        weatherDesc.contains("Hujan Ringan", ignoreCase = true) && kelembapan > 80 ->
            Rekomendasi(
                rekomendasi = "Cuaca lembap dan hujan bisa memicu sinus. Gunakan humidifier di dalam rumah dan hindari tempat berdebu.",
                icon = R.drawable.sinus
            )

        // Udara kering menyebabkan iritasi sinus
        kelembapan < 30 ->
            Rekomendasi(
                rekomendasi = "Udara kering dapat memperburuk sinus. Gunakan pelembap udara dan minum cukup air.",
                icon = R.drawable.sinus
            )

        // Suhu ekstrem panas atau dingin bisa memperburuk sinus
        suhu > 35 ->
            Rekomendasi(
                rekomendasi = "Cuaca panas bisa membuat sinus terasa lebih berat. Hindari terlalu lama di luar ruangan dan perbanyak minum air.",
                icon = R.drawable.sinus3
            )

        suhu < 15 ->
            Rekomendasi(
                rekomendasi = "Udara dingin dapat memperburuk sinus. Gunakan pakaian hangat dan hindari perubahan suhu yang mendadak.",
                icon = R.drawable.sinus4
            )

        // Angin kencang membawa debu dan alergen
        kecepatanAngin > 20.0 ->
            Rekomendasi(
                rekomendasi = "Angin kencang bisa membawa alergen yang memicu sinus. Gunakan masker saat keluar rumah.",
                icon = R.drawable.sinus5
            )

        // Tutupan awan tinggi, indikasi kelembapan tinggi
        tutupanAwan > 80 && kelembapan > 75 ->
            Rekomendasi(
                rekomendasi = "Langit mendung dan udara lembap dapat memperburuk sinus. Pastikan sirkulasi udara baik di dalam rumah.",
                icon = R.drawable.sinus6
            )

        else ->
            Rekomendasi(
                rekomendasi = "Cuaca cukup aman untuk penderita sinus, tapi tetap jaga kelembapan ruangan dan hindari paparan alergen.",
                icon = R.drawable.sinus7
            )
    }
}