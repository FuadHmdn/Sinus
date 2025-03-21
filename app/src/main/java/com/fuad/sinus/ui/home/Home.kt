package com.fuad.sinus.ui.home

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.fuad.sinus.R
import com.fuad.sinus.data.Rekomendasi
import com.fuad.sinus.di.Injection
import com.fuad.sinus.ui.theme.Blue
import com.fuad.sinus.ui.theme.SinusTheme
import com.fuad.sinus.ui.viewModel.ViewModelFactory
import com.fuad.sinus.ui.viewModel.WeatherViewModel

@Composable
fun Home(
    context: Context,
    viewModel: WeatherViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideWeatherRepository(context), null)
    ),
    navigateToDetail: () -> Unit
) {
    val cuacaState = viewModel.cuaca.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.getCuaca()
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        when (val cuaca = cuacaState.value) {
            null -> HomeComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
                cuaca = "Memuat..",
                suhu = 0,
                kelembapan = 0,
                kecepatanAngin = 0.0,
                foto = "https://api-apps.bmkg.go.id/storage/icon/cuaca/berawan-am.svg",
                rekomendasi = viewModel.getRekomendasi(
                    suhu = 0,
                    kelembapan = 0,
                    weatherDesc = "Cerah",
                    kecepatanAngin = 0.0,
                    tutupanAwan = 0
                ),
                context = context
            )

            else -> HomeComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
                cuaca = cuaca.cuaca,
                suhu = cuaca.suhu,
                kelembapan = cuaca.kelembapan,
                kecepatanAngin = cuaca.kecepatanAngin,
                foto = cuaca.foto,
                rekomendasi = viewModel.getRekomendasi(
                    suhu = cuaca.suhu,
                    kelembapan = cuaca.kelembapan,
                    weatherDesc = cuaca.cuaca,
                    kecepatanAngin = cuaca.kecepatanAngin,
                    tutupanAwan = cuaca.tutupanAwan
                ),
                context = context
            )
        }
        
        DiagnoseSinus(
            modifier = Modifier.padding(16.dp)
                .clickable {
                    navigateToDetail()
                }
        )
    }
}

@Composable
fun HomeComponent(
    modifier: Modifier,
    suhu: Int,
    kelembapan: Int,
    cuaca: String,
    kecepatanAngin: Double,
    foto: String,
    rekomendasi: Rekomendasi,
    context: Context
) {
    Column(
        modifier = Modifier
    ) {
        CuacaCard(
            modifier = modifier,
            suhu = suhu,
            kelembapan = kelembapan,
            cuaca = cuaca,
            kecepatanAngin = kecepatanAngin,
            foto = foto,
            context = context
        )

        Rekomendasi(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            rekomendasi = rekomendasi,
        )
    }
}

@Composable
fun CuacaCard(
    modifier: Modifier = Modifier,
    suhu: Int,
    kelembapan: Int,
    cuaca: String,
    kecepatanAngin: Double,
    foto: String,
    context: Context
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        modifier = Modifier,
                        text = "$suhu°C",
                        fontSize = 60.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Blue
                    )

                    Text(
                        modifier = Modifier,
                        text = cuaca,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Blue
                    )
                }

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(foto)
                        .decoderFactory(SvgDecoder.Factory())
                        .crossfade(false)
                        .build(),
                    contentDescription = cuaca,
                    placeholder = painterResource(R.drawable.loading),
                    error = painterResource(R.drawable.error),
                    modifier = Modifier.size(100.dp)
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 6.dp))

            Text(
                modifier = Modifier,
                text = "Kelembapan $kelembapan%",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Text(
                modifier = Modifier,
                text = "Kecepatan Angin $kecepatanAngin km/jam",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun Rekomendasi(
    modifier: Modifier = Modifier,
    rekomendasi: Rekomendasi,
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = rekomendasi.rekomendasi,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Image(
                modifier = Modifier.size(64.dp),
                painter = painterResource(rekomendasi.icon),
                contentDescription = null,
            )
        }
    }
}

@Composable
fun DiagnoseSinus(
    modifier: Modifier
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Prediksi Alergi Anda Sekarang",
                fontSize = 20.sp,
                color = Blue,
                fontWeight = FontWeight.SemiBold
            )

            Image(
                modifier = Modifier.size(60.dp),
                painter = painterResource(R.drawable.diagnose),
                contentDescription = null,
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomePreview() {
    SinusTheme {
        Column {
            HomeComponent(
                modifier = Modifier.padding(
                    top = 32.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ),
                suhu = 25,
                kelembapan = 80,
                cuaca = "Udara Kabur",
                kecepatanAngin = 2.2,
                foto = "https://api-apps.bmkg.go.id/storage/icon/cuaca/berawan-am.svg",
                context = LocalContext.current,
                rekomendasi = Rekomendasi(
                    rekomendasi = "Hindari keluar rumah! Kelembapan tinggi dan cuaca buruk bisa memperparah sinus. Gunakan masker jika terpaksa keluar.",
                    icon = R.drawable.sinus
                )
            )

            DiagnoseSinus(modifier = Modifier.padding(16.dp))
        }
    }
}