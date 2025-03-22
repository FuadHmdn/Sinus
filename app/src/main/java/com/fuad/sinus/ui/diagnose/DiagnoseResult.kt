package com.fuad.sinus.ui.diagnose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fuad.sinus.R
import com.fuad.sinus.ui.theme.Blue2

@Composable
fun DiagnoseResult(
    modifier: Modifier,
    d1: Float,
    d2: Float,
    d3: Float,
    d4: Float,
    navigateToHome: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Hasil Diagnosis",
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(32.dp))

        ListPersentase(
            modifier = Modifier,
            list = listOf(
                Triple(
                    "Sinusitis Maksilaris",
                    d1,
                    "Anda mungkin merasakan nyeri di pipi, sakit gigi, dan hidung tersumbat akibat infeksi di sinus bagian pipi. Biasanya disebabkan oleh flu atau alergi yang membuat lendir terjebak dan menyebabkan peradangan. Solusi: Anda bisa menggunakan dekongestan untuk melegakan hidung, melakukan bilas hidung dengan larutan garam, dan jika infeksinya disebabkan oleh bakteri, dokter mungkin akan meresepkan antibiotik."
                ),
                Triple(
                    "Sinusitis Frontalis",
                    d2,
                    "Jika Anda mengalami sakit kepala di dahi, nyeri saat menunduk, dan hidung tersumbat, bisa jadi Anda mengalami sinusitis di bagian dahi. Solusi: Untuk meredakannya, Anda bisa mengompres hangat area dahi, minum obat pereda nyeri seperti paracetamol, serta rutin membilas hidung dengan larutan garam agar lendir lebih mudah keluar."
                ),
                Triple(
                    "Sinusitis Etmoidalis",
                    d3,
                    "Infeksi sinus ini terjadi di antara mata dan bisa menyebabkan nyeri di sekitar hidung, sakit kepala, dan demam. Solusi: Anda bisa menggunakan semprotan hidung yang mengandung kortikosteroid untuk mengurangi peradangan, banyak minum air agar lendir lebih encer, dan jika disebabkan oleh bakteri, dokter mungkin akan memberikan antibiotik."
                ),
                Triple(
                    "Sinusitis Sfenoidalis",
                    d4,
                    "Jika Anda merasakan nyeri di bagian belakang kepala atau mata, pusing, dan penglihatan kabur, bisa jadi sinus di bagian belakang tengkorak Anda sedang mengalami infeksi. Solusi: Karena letaknya lebih dalam, sinusitis ini bisa berbahaya jika tidak ditangani dengan baik. Segera periksa ke dokter untuk mendapatkan perawatan yang sesuai, seperti obat pereda nyeri, antibiotik, atau semprotan hidung yang dapat membantu mengurangi peradangan."
                ),
            ),
            navigateToHome = navigateToHome
        )
    }
}

@Composable
fun ListPersentase(
    modifier: Modifier = Modifier,
    list: List<Triple<String, Float, String>>,
    navigateToHome: () -> Unit
) {
    val sortedList = list.sortedByDescending { it.second }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(sortedList) { index, item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AnimatedCircularPercentage(item.second * 100)
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = item.first,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    )
                    if (index == 0 && item.second > 0.1) {
                        Text(
                            text = item.third,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Justify
                        )
                        Spacer(Modifier.height(16.dp))
                    }
                    Spacer(Modifier.height(16.dp))
                }
            }

            item {
                if (sortedList.first().second <= 0.0001F) {
                    Text(
                        text = "Berdasarkan hasil analisis, tidak ditemukan indikasi yang sesuai dengan jenis-jenis sinusitis. Gejala yang Anda alami mungkin disebabkan oleh kondisi lain. Jika keluhan masih berlanjut atau semakin parah, disarankan untuk berkonsultasi dengan dokter untuk pemeriksaan lebih lanjut.",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Justify
                    )
                }
                Spacer(Modifier.height(24.dp))
                Button(
                    onClick = {
                        navigateToHome()
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
                        text = "Selesai",
                        fontSize = 14.sp
                    )
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}


@Composable
fun AnimatedCircularPercentage(targetProgress: Float) {
    var progress by remember { mutableStateOf(0f) }

    // Animasi dari 0 ke targetProgress
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 1000) // 1 detik
    )

    LaunchedEffect(targetProgress) {
        progress = targetProgress
    }

    Box(
        modifier = Modifier
            .size(120.dp)
            .padding(8.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 34f
            val size = Size(size.width, size.height)

            // Background Circle
            drawArc(
                color = Color.LightGray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )

            // Progress Circle
            drawArc(
                color = Color.Blue,
                startAngle = -90f,
                sweepAngle = (animatedProgress / 100) * 360,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }

        Text(
            text = "${animatedProgress.toInt()}%",
            fontSize = 22.sp,
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(androidx.compose.ui.Alignment.Center)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HasilPrev() {
    DiagnoseResult(
        Modifier
            .padding(top = 42.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        0.8F,
        0.8F,
        0.8F,
        0.9F
    ) {}
}