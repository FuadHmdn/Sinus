package com.fuad.sinus.ui.home

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.fuad.sinus.R
import com.fuad.sinus.data.Artikel
import com.fuad.sinus.data.Rekomendasi
import com.fuad.sinus.di.Injection
import com.fuad.sinus.ui.theme.Blue
import com.fuad.sinus.ui.theme.SinusTheme
import com.fuad.sinus.ui.viewModel.ViewModelFactory
import com.fuad.sinus.ui.viewModel.WeatherViewModel
import kotlin.math.max

@Composable
fun Home(
    context: Context,
    viewModel: WeatherViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideWeatherRepository(context), null)
    ),
    navigateToDetail: () -> Unit,
    navigateToDetailArtikel: (Int, Int, Int) -> Unit
) {
    val cuacaState = viewModel.cuaca.observeAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.getCuaca()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
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
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    navigateToDetail()
                }
        )

        Text(
            text = "Baca artikel",
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(8.dp))
        Article(){ a, b, c ->
            navigateToDetailArtikel(a,b,c)
        }
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
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier
    ) {
        Column(
            modifier = modifier
        ) {
            Text(
                text = "Prediksi Cuaca 1 jam kedepan",
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            )
            Text(
                text = "Bandar Lampung",
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
            )
            Spacer(Modifier.height(8.dp))

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
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        color = Blue
                    )

                    Text(
                        modifier = Modifier,
                        text = cuaca,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
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
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            )

            Text(
                modifier = Modifier,
                text = "Kecepatan Angin $kecepatanAngin km/jam",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
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
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
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
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
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
fun Article(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int, Int, Int) -> Unit
) {
    val artikel = listOf(
        Artikel(
            title = R.string.artikel_1_title,
            isi = R.string.artikel_1_content,
            banner = R.drawable.sinus_illustration
        ),
        Artikel(
            title = R.string.artikel_2_title,
            isi = R.string.artikel_2_content,
            banner = R.drawable.sinus_illustration_4
        ),
        Artikel(
            title = R.string.artikel_3_title,
            isi = R.string.artikel_3_content,
            banner = R.drawable.sinus_illustration_2
        ),
        Artikel(
            title = R.string.artikel_4_title,
            isi = R.string.artikel_4_content,
            banner = R.drawable.sinus_illustration_3
        ),
    )
    Column(
        modifier = modifier
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
        ) {
            itemsIndexed(artikel) { index, item ->
                Column(
                    modifier = Modifier.width(240.dp)
                ) {
                    Card(
                        modifier = Modifier.size(width = 240.dp, height = 130.dp)
                            .clickable { navigateToDetail(item.banner, item.isi, item.title) }
                    ) {
                        Image(
                            painter = painterResource(item.banner),
                            contentDescription = stringResource(item.title),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Text(
                        text = stringResource(item.title),
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

@Composable
fun DiagnoseSinus(
    modifier: Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
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
                text = "Sinusitis atau Bukan? Cari Tahu di Sini!",
                fontSize = 20.sp,
                color = Blue,
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
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
            Text(
                text = "Baca artikel",
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            Spacer(Modifier.height(8.dp))
            Article{ a,b,c ->

            }
        }
    }
}