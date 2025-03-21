package com.fuad.sinus.ui.diagnose

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fuad.sinus.data.repository.DiagnoseRepository

class DiagnoseViewModel(
    private val diagnoseRepository: DiagnoseRepository
) : ViewModel() {

    val maksilaris: LiveData<Double?> get() = diagnoseRepository.maksilaris
    val frontalis: LiveData<Double?> get() = diagnoseRepository.frontalis
    val etmoidalis: LiveData<Double?> get() = diagnoseRepository.etmoidalis
    val sfenoidalis: LiveData<Double?> get() = diagnoseRepository.sfenoidalis

    var nyeriWajah = 0
    var hidungTersumbatLendirKuning = 0
    var lendirMengalirSedikit = 0
    var berkurangnyaDayaPengecap = 0

    var nafasBerbau = 0
    var hidungTersumbatBertahun = 0
    var nyeriMenelan = 0
    var nyeriPipiBawahMata = 0

    var sakitGigiNyeri = 0
    var berkurangnyaDayaPenciuman = 0
    var demamParahMalamHari = 0
    var selaputLendirBengkak = 0

    var sakitKepalaMenunduk = 0
    var nyeriDahiBawahAlis = 0
    var nyeriAntaraMata = 0
    var batukParahMalamHari = 0

    var seringTerkenaAsma = 0
    var nyeriSekitarHidung = 0
    var sakitLeher = 0
    var nyeriTelinga = 0


    fun diagnoseSinus() {
        diagnoseRepository.diagnose(
            nyeriWajah = nyeriWajah,
            hidungTersumbatLendirKuning = hidungTersumbatLendirKuning,
            lendirMengalirSedikit = lendirMengalirSedikit,
            berkurangnyaDayaPengecap = berkurangnyaDayaPengecap,
            nafasBerbau = nafasBerbau,
            hidungTersumbatBertahun = hidungTersumbatBertahun,
            nyeriMenelan = nyeriMenelan,
            nyeriPipiBawahMata = nyeriPipiBawahMata,
            sakitGigiNyeri = sakitGigiNyeri,
            berkurangnyaDayaPenciuman = berkurangnyaDayaPenciuman,
            demamParahMalamHari = demamParahMalamHari,
            selaputLendirBengkak = selaputLendirBengkak,
            sakitKepalaMenunduk = sakitKepalaMenunduk,
            nyeriDahiBawahAlis = nyeriDahiBawahAlis,
            nyeriAntaraMata = nyeriAntaraMata,
            batukParahMalamHari = batukParahMalamHari,
            seringTerkenaAsma = seringTerkenaAsma,
            nyeriSekitarHidung = nyeriSekitarHidung,
            sakitLeher = sakitLeher,
            nyeriTelinga = nyeriTelinga
        )
    }
}