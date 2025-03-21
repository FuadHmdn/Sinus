package com.fuad.sinus.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DiagnoseRepository {

    private val _maksilaris = MutableLiveData<Double?>()
    val maksilaris: LiveData<Double?> get() = _maksilaris

    private val _frontalis = MutableLiveData<Double?>()
    val frontalis: LiveData<Double?> get() = _frontalis

    private val _etmoidalis = MutableLiveData<Double?>()
    val etmoidalis: LiveData<Double?> get() = _etmoidalis

    private val _sfenoidalis = MutableLiveData<Double?>()
    val sfenoidalis: LiveData<Double?> get() = _sfenoidalis

    fun diagnose(
        nyeriWajah: Int,
        hidungTersumbatLendirKuning: Int,
        lendirMengalirSedikit: Int,
        berkurangnyaDayaPengecap: Int,
        nafasBerbau: Int,
        hidungTersumbatBertahun: Int,
        nyeriMenelan: Int,
        nyeriPipiBawahMata: Int,
        sakitGigiNyeri: Int,
        berkurangnyaDayaPenciuman: Int,
        demamParahMalamHari: Int,
        selaputLendirBengkak: Int,
        sakitKepalaMenunduk: Int,
        nyeriDahiBawahAlis: Int,
        nyeriAntaraMata: Int,
        batukParahMalamHari: Int,
        seringTerkenaAsma: Int,
        nyeriSekitarHidung: Int,
        sakitLeher: Int,
        nyeriTelinga: Int
    ) {
        _maksilaris.value = diagnoseMaksilaris(
            nyeriWajah,
            hidungTersumbatLendirKuning,
            lendirMengalirSedikit,
            berkurangnyaDayaPengecap,
            nafasBerbau,
            hidungTersumbatBertahun,
            nyeriMenelan,
            nyeriPipiBawahMata,
            sakitGigiNyeri
        )

        _frontalis.value = diagnoseFrontalis(
            nyeriWajah,
            hidungTersumbatLendirKuning,
            berkurangnyaDayaPenciuman,
            berkurangnyaDayaPengecap,
            demamParahMalamHari,
            selaputLendirBengkak,
            hidungTersumbatBertahun,
            sakitKepalaMenunduk,
            nyeriDahiBawahAlis,
            nyeriAntaraMata
        )

        _etmoidalis.value = diagnoseEtimodalis(
            nyeriWajah,
            hidungTersumbatLendirKuning,
            berkurangnyaDayaPenciuman,
            berkurangnyaDayaPengecap,
            batukParahMalamHari,
            selaputLendirBengkak,
            hidungTersumbatBertahun,
            seringTerkenaAsma,
            nyeriSekitarHidung
        )

        _sfenoidalis.value = diagnoseSfenoidalis(
            nyeriWajah,
            hidungTersumbatLendirKuning,
            hidungTersumbatBertahun,
            sakitLeher,
            nyeriTelinga,
            nyeriMenelan
        )

    }

   private fun diagnoseMaksilaris(
        nyeriWajah: Int,
        hidungTersumbatLendirKuning: Int,
        lendirMengalirSedikit: Int,
        berkurangDayaPengecap: Int,
        nafasBerbau: Int,
        hidungTersumbatLama: Int,
        nyeriMenelan: Int,
        nyeriPipiBawahMata: Int,
        sakitGigiNyeri: Int
    ): Double {
        val cfNyeriWajah = if (nyeriWajah != 0) (0.3 - 0.5) else 0.0
        val cfHidungTersumbatLendirKuning = if (hidungTersumbatLendirKuning != 0) (0.4 - 0.3) else 0.0
        val cfLendirMengalirSedikit = if (lendirMengalirSedikit != 0) (0.8 - 0.02) else 0.0
        val cfBerkurangDayaPengecap = if (berkurangDayaPengecap != 0) (0.4 - 0.2) else 0.0
        val cfNafasBerbau = if (nafasBerbau != 0) (0.6 - 0.02) else 0.0
        val cfHidungTersumbatLama = if (hidungTersumbatLama != 0) (0.4 - 0.2) else 0.0
        val cfNyeriMenelan = if (nyeriMenelan != 0) (0.8 - 0.02) else 0.0
        val cfNyeriPipiBawahMata = if (nyeriPipiBawahMata != 0) (0.7 - 0.02) else 0.0
        val cfSakitGigiNyeri = if (sakitGigiNyeri != 0) (0.7 - 0.02) else 0.0

        // List semua nilai CF yang dihitung
        val cfList = listOf(
            cfNyeriWajah,
            cfHidungTersumbatLendirKuning,
            cfLendirMengalirSedikit,
            cfBerkurangDayaPengecap,
            cfNafasBerbau,
            cfHidungTersumbatLama,
            cfNyeriMenelan,
            cfNyeriPipiBawahMata,
            cfSakitGigiNyeri
        )

        // Gunakan rumus CF combine
        var certaintyFactor = 0.0
        for (cf in cfList) {
            certaintyFactor += cf * (1 - certaintyFactor)
        }

        return certaintyFactor
    }

    private fun diagnoseFrontalis(
        nyeriWajah: Int,
        hidungTersumbatLendirKuning: Int,
        berkurangnyaDayaPenciuman: Int,
        berkurangnyaDayaPengecap: Int,
        demamParahMalamHari: Int,
        selaputLendirBengkak: Int,
        hidungTersumbatBertahun: Int,
        sakitKepalaTunduk: Int,
        nyeriDahiBawahAlis: Int,
        nyeriAntaraMata: Int
    ): Double {

        val cfList = mutableListOf<Double>()

        if (nyeriWajah != 0) cfList.add(0.3 - 0.5)
        if (hidungTersumbatLendirKuning != 0) cfList.add(0.4 - 0.3)
        if (berkurangnyaDayaPenciuman != 0) cfList.add(0.3 - 0.4)
        if (berkurangnyaDayaPengecap != 0) cfList.add(0.4 - 0.2)
        if (demamParahMalamHari != 0) cfList.add(0.8 - 0.02)
        if (selaputLendirBengkak != 0) cfList.add(0.5 - 0.2)
        if (hidungTersumbatBertahun != 0) cfList.add(0.4 - 0.2)
        if (sakitKepalaTunduk != 0) cfList.add(0.8 - 0.02)
        if (nyeriDahiBawahAlis != 0) cfList.add(0.7 - 0.02)
        if (nyeriAntaraMata != 0) cfList.add(0.8 - 0.02)

        // Menggabungkan CF menggunakan rumus certainty factor
        var hasil = 0.0
        for (cf in cfList) {
            hasil = hasil + (cf * (1 - hasil))
        }

        return hasil
    }

    private fun diagnoseEtimodalis(
        nyeriWajah: Int,
        hidungTersumbatLendirKuning: Int,
        berkurangnyaDayaPenciuman: Int,
        berkurangnyaDayaPengecap: Int,
        batukParahMalamHari: Int,
        selaputLendirBengkak: Int,
        hidungTersumbatBertahun: Int,
        seringTerkenaAsma: Int,
        nyeriSekitarHidung: Int
    ): Double {

        val cfList = mutableListOf<Double>()

        if (nyeriWajah != 0) cfList.add(0.3 - 0.5)
        if (hidungTersumbatLendirKuning != 0) cfList.add(0.4 - 0.3)
        if (berkurangnyaDayaPenciuman != 0) cfList.add(0.3 - 0.4)
        if (berkurangnyaDayaPengecap != 0) cfList.add(0.4 - 0.2)
        if (batukParahMalamHari != 0) cfList.add(0.8 - 0.02)
        if (selaputLendirBengkak != 0) cfList.add(0.5 - 0.2)
        if (hidungTersumbatBertahun != 0) cfList.add(0.4 - 0.2)
        if (seringTerkenaAsma != 0) cfList.add(0.8 - 0.02)
        if (nyeriSekitarHidung != 0) cfList.add(0.8 - 0.02)

        // Menggabungkan CF menggunakan rumus certainty factor
        var hasil = 0.0
        for (cf in cfList) {
            hasil = hasil + (cf * (1 - hasil))
        }

        return hasil
    }

    private fun diagnoseSfenoidalis(
        nyeriWajah: Int,
        hidungTersumbatLendirKuning: Int,
        hidungTersumbatBertahun: Int,
        sakitLeher: Int,
        nyeriTelinga: Int,
        nyeriMenelan: Int
    ): Double {

        val cfList = mutableListOf<Double>()

        if (nyeriWajah != 0) cfList.add(0.3 - 0.5)
        if (hidungTersumbatLendirKuning != 0) cfList.add(0.4 - 0.3)
        if (hidungTersumbatBertahun != 0) cfList.add(0.4 - 0.2)
        if (sakitLeher != 0) cfList.add(0.8 - 0.02)
        if (nyeriTelinga != 0) cfList.add(0.8 - 0.02)
        if (nyeriMenelan != 0) cfList.add(0.7 - 0.02)

        // Menggabungkan CF menggunakan rumus certainty factor
        var hasil = 0.0
        for (cf in cfList) {
            hasil = hasil + (cf * (1 - hasil))
        }

        return hasil
    }
}