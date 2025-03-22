package com.fuad.sinus.ui.diagnose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fuad.sinus.R
import com.fuad.sinus.di.Injection
import com.fuad.sinus.ui.theme.Blue2
import com.fuad.sinus.ui.theme.SinusTheme
import com.fuad.sinus.ui.viewModel.ViewModelFactory

@Composable
fun Diagnose(
    modifier: Modifier = Modifier,
    viewModel: DiagnoseViewModel = viewModel(
        factory = ViewModelFactory(null, Injection.provideDiagnoseRepository())
    ),
    navigateToResult: (Double, Double, Double, Double) -> Unit
) {
    val scrollState = rememberScrollState()
    var step by remember { mutableStateOf(1) }
    var progress by remember { mutableFloatStateOf(0.2F) }

    var checkedStates1 by remember { mutableStateOf(List(4) { false }) }
    var checkedStates2 by remember { mutableStateOf(List(4) { false }) }
    var checkedStates3 by remember { mutableStateOf(List(4) { false }) }
    var checkedStates4 by remember { mutableStateOf(List(4) { false }) }
    var checkedStates5 by remember { mutableStateOf(List(4) { false }) }

    LaunchedEffect(Unit) { viewModel.removeResult() }

    Column(
        modifier = Modifier
            .padding(top = 34.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .verticalScroll(
                scrollState
            )
    ) {
        TopProgressBar(progress)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Pilih Gejala yang Dialami:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        when (step) {
            1 -> {
                Diagnose1(
                    checkedStates = checkedStates1,
                    onCheckedChange = { index, isChecked ->
                        checkedStates1 = checkedStates1.toMutableList().also { it[index] = isChecked }

                        when (index) {
                            0 -> viewModel.nyeriWajah = if (isChecked) 1 else 0
                            1 -> viewModel.hidungTersumbatLendirKuning = if (isChecked) 1 else 0
                            2 -> viewModel.lendirMengalirSedikit = if (isChecked) 1 else 0
                            3 -> viewModel.berkurangnyaDayaPengecap = if (isChecked) 1 else 0
                        }

                        Log.d("CEK", "CheckedStates: $checkedStates1 ${viewModel.nyeriWajah}")
                    }
                )
            }
            2 -> {
                Diagnose2(
                    checkedStates = checkedStates2,
                    onCheckedChange = { index, isChecked ->
                        checkedStates2 = checkedStates2.toMutableList().also { it[index] = isChecked }

                        when (index) {
                            0 -> viewModel.nafasBerbau = if (isChecked) 1 else 0
                            1 -> viewModel.hidungTersumbatBertahun = if (isChecked) 1 else 0
                            2 -> viewModel.nyeriMenelan = if (isChecked) 1 else 0
                            3 -> viewModel.nyeriPipiBawahMata = if (isChecked) 1 else 0
                        }

                        Log.d("CEK", "CheckedStates: $checkedStates2 ${viewModel.nafasBerbau}")
                    }
                )
            }
            3 -> {
                Diagnose3(
                    checkedStates = checkedStates3,
                    onCheckedChange = { index, isChecked ->
                        checkedStates3 = checkedStates3.toMutableList().also { it[index] = isChecked }

                        when (index) {
                            0 -> viewModel.sakitGigiNyeri = if (isChecked) 1 else 0
                            1 -> viewModel.berkurangnyaDayaPenciuman = if (isChecked) 1 else 0
                            2 -> viewModel.demamParahMalamHari = if (isChecked) 1 else 0
                            3 -> viewModel.selaputLendirBengkak = if (isChecked) 1 else 0
                        }

                        Log.d("CEK", "CheckedStates: $checkedStates3 ${viewModel.sakitGigiNyeri}")
                    }
                )
            }
            4 -> {
                Diagnose4(
                    checkedStates = checkedStates4,
                    onCheckedChange = { index, isChecked ->
                        checkedStates4 = checkedStates4.toMutableList().also { it[index] = isChecked }

                        when (index) {
                            0 -> viewModel.sakitKepalaMenunduk = if (isChecked) 1 else 0
                            1 -> viewModel.nyeriDahiBawahAlis = if (isChecked) 1 else 0
                            2 -> viewModel.nyeriAntaraMata = if (isChecked) 1 else 0
                            3 -> viewModel.batukParahMalamHari = if (isChecked) 1 else 0
                        }

                        Log.d("CEK", "CheckedStates: $checkedStates4 ${viewModel.sakitKepalaMenunduk}")
                    }
                )
            }
            5 -> {
                Diagnose5(
                    checkedStates = checkedStates5,
                    onCheckedChange = { index, isChecked ->
                        checkedStates5 = checkedStates5.toMutableList().also { it[index] = isChecked }

                        when (index) {
                            0 -> viewModel.seringTerkenaAsma = if (isChecked) 1 else 0
                            1 -> viewModel.nyeriSekitarHidung = if (isChecked) 1 else 0
                            2 -> viewModel.sakitLeher = if (isChecked) 1 else 0
                            3 -> viewModel.nyeriTelinga = if (isChecked) 1 else 0
                        }

                        Log.d("CEK", "CheckedStates: $checkedStates5 ${viewModel.seringTerkenaAsma}")
                    }
                )
            }
            6 -> {
                Diagnose5(
                    checkedStates = checkedStates5,
                    onCheckedChange = { index, isChecked ->
                        checkedStates5 = checkedStates5.toMutableList().also { it[index] = isChecked }

                        when (index) {
                            0 -> viewModel.seringTerkenaAsma = if (isChecked) 1 else 0
                            1 -> viewModel.nyeriSekitarHidung = if (isChecked) 1 else 0
                            2 -> viewModel.sakitLeher = if (isChecked) 1 else 0
                            3 -> viewModel.nyeriTelinga = if (isChecked) 1 else 0
                        }

                    }
                )
                viewModel.diagnoseSinus()
                    .also {
                        navigateToResult(
                            viewModel.maksilaris,
                            viewModel.frontalis,
                            viewModel.etmoidalis,
                            viewModel.sfenoidalis
                        )
                        step--
                        Log.d("HASIL", viewModel.maksilaris.toString())
                        Log.d("HASIL", viewModel.nyeriWajah.toString())
                        Log.d("HASIL", "SELESAI")
                    }
            }
        }

        Spacer(modifier = Modifier.height(84.dp))

        if (step != 1){
            BackButton {
                step--
                progress -= 0.2F
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                step++
                progress += 0.2F
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue2,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Selanjutnya",
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = {onClick()},
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue2,
            contentColor = Color.White
        )
    ) {
        Text(
            text = "Kembali",
            fontSize = 14.sp
        )
    }
}

@Composable
fun Diagnose1(
    modifier: Modifier = Modifier,
    checkedStates: List<Boolean>,
    onCheckedChange: (Int, Boolean) -> Unit
) {
    val items = listOf(
        "Nyeri dan merasa tertekan pada wajah",
        "Hidung tersumbat lendir berwarna kuning",
        "Lendir mengalir dalam jumlah kecil di dalam hidung",
        "Berkurangnya daya pengecap"
    )

    Column(modifier = modifier.padding(16.dp)) {
        items.forEachIndexed { index, text ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCheckedChange(index, !checkedStates[index]) }
                    .padding(vertical = 8.dp)
            ) {
                Checkbox(
                    checked = checkedStates[index],
                    onCheckedChange = { isChecked -> onCheckedChange(index, isChecked) }
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun Diagnose2(
    modifier: Modifier = Modifier,
    checkedStates: List<Boolean>,
    onCheckedChange: (Int, Boolean) -> Unit
) {
    val items = listOf(
        "Nafas berbau",
        "Hidung Tersumbat Bertahun - tahun",
        "Nyeri untuk menelan",
        "Nyeri pipi dibawah mata"
    )

    Column(modifier = modifier.padding(16.dp)) {
        items.forEachIndexed { index, text ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCheckedChange(index, !checkedStates[index]) }
                    .padding(vertical = 8.dp)
            ) {
                Checkbox(
                    checked = checkedStates[index],
                    onCheckedChange = { isChecked -> onCheckedChange(index, isChecked) }
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun Diagnose3(
    modifier: Modifier = Modifier,
    checkedStates: List<Boolean>,
    onCheckedChange: (Int, Boolean) -> Unit
) {
    val items = listOf(
        "Sakit Gigi atau nyeri",
        "Berkurangnya daya penciuman",
        "Demam yg parah saat malam hari",
        "Selapu lendir memerah dan bengkak"
    )

    Column(modifier = modifier.padding(16.dp)) {
        items.forEachIndexed { index, text ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCheckedChange(index, !checkedStates[index]) }
                    .padding(vertical = 8.dp)
            ) {
                Checkbox(
                    checked = checkedStates[index],
                    onCheckedChange = { isChecked -> onCheckedChange(index, isChecked) }
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun Diagnose4(
    modifier: Modifier = Modifier,
    checkedStates: List<Boolean>,
    onCheckedChange: (Int, Boolean) -> Unit
) {
    val items = listOf(
        "Sakit Kepala hebat saat Kepala ditundkan ke depan",
        "Nyeri pada dahi bawah dan alis mata ",
        "Nyeri antara mata",
        "Batuk parah saat malam hari"
    )

    Column(modifier = modifier.padding(16.dp)) {
        items.forEachIndexed { index, text ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCheckedChange(index, !checkedStates[index]) }
                    .padding(vertical = 8.dp)
            ) {
                Checkbox(
                    checked = checkedStates[index],
                    onCheckedChange = { isChecked -> onCheckedChange(index, isChecked) }
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun Diagnose5(
    modifier: Modifier = Modifier,
    checkedStates: List<Boolean>,
    onCheckedChange: (Int, Boolean) -> Unit
) {
    val items = listOf(
        "Sering Terkena Asma",
        "Nyeri di Sekitaran Hidung",
        "Sakit pada Leher",
        "Nyeri tertekan pada telinga"
    )

    Column(modifier = modifier.padding(16.dp)) {
        items.forEachIndexed { index, text ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCheckedChange(index, !checkedStates[index]) }
                    .padding(vertical = 8.dp)
            ) {
                Checkbox(
                    checked = checkedStates[index],
                    onCheckedChange = { isChecked -> onCheckedChange(index, isChecked) }
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}


@Composable
fun TopProgressBar(progress: Float) {
    LinearProgressIndicator(
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp),
        color = Blue2
    )
}

@Composable
fun WelcomeDiagnose(
    modifier: Modifier,
    navigateToDiagnose: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {
        Text(
            text = "Selamat datang\nBagaimana kabar anda hari ini?",
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            color = Blue2,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Bantu kami identifikasi gejala sinus dengan cepat. Mulai sekarang!",
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        Image(
            painter = painterResource(R.drawable.diagnose_online),
            contentDescription = null,
            modifier = Modifier
                .size(310.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(88.dp))
        Button(
            onClick = {
                navigateToDiagnose()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue2,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Selanjutnya",
                fontSize = 14.sp
            )
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DiagnosePrev() {
    SinusTheme {
        Diagnose1(
            modifier = Modifier
                .padding(top = 32.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            checkedStates = listOf(false, false),
            { a, b ->}
        )
    }
}