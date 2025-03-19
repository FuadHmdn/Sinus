package com.fuad.sinus.ui.home

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fuad.sinus.di.Injection
import com.fuad.sinus.ui.viewModel.ViewModelFactory
import com.fuad.sinus.ui.viewModel.WeatherViewModel

@Composable
fun Home(
    context: Context,
    viewModel: WeatherViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideWeatherRepository(context))
    )
) {
    val cuacaState = viewModel.cuaca.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.getCuaca()
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        when (val cuaca = cuacaState.value) {
            null -> Text("Loading...")
            else -> HomeComponent(
                modifier = Modifier.fillMaxWidth(),
                text = cuaca.foto
            )
        }
    }
}

@Composable
fun HomeComponent(modifier: Modifier = Modifier, text: String) {
    Column(
        modifier = modifier
    ) {
        Text(text)
        Text("Text")
    }
}

@Preview
@Composable
private fun HomePreview() {
    HomeComponent(modifier = Modifier, text = "Fuad")
}