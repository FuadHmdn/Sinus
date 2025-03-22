package com.fuad.sinus.ui.diagnose

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fuad.sinus.data.repository.DiagnoseRepository
import kotlinx.coroutines.launch

class DiagnoseViewModel(
    private val diagnoseRepository: DiagnoseRepository
) : ViewModel() {

    val maksilaris get() = diagnoseRepository.maksilaris
    val frontalis get() = diagnoseRepository.frontalis
    val etmoidalis get() = diagnoseRepository.etmoidalis
    val sfenoidalis get() = diagnoseRepository.sfenoidalis

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
        viewModelScope.launch {
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

    fun removeResult(){
        viewModelScope.launch {
            diagnoseRepository.maksilaris = 0.0
            diagnoseRepository.frontalis = 0.0
            diagnoseRepository. etmoidalis = 0.0
            diagnoseRepository.sfenoidalis = 0.0

            nyeriWajah = 0
            hidungTersumbatLendirKuning = 0
            lendirMengalirSedikit = 0
            berkurangnyaDayaPengecap = 0

            nafasBerbau = 0
            hidungTersumbatBertahun = 0
            nyeriMenelan = 0
            nyeriPipiBawahMata = 0

            sakitGigiNyeri = 0
            berkurangnyaDayaPenciuman = 0
            demamParahMalamHari = 0
            selaputLendirBengkak = 0

            sakitKepalaMenunduk = 0
            nyeriDahiBawahAlis = 0
            nyeriAntaraMata = 0
            batukParahMalamHari = 0

            seringTerkenaAsma = 0
            nyeriSekitarHidung = 0
            sakitLeher = 0
            nyeriTelinga = 0

        }
    }
}