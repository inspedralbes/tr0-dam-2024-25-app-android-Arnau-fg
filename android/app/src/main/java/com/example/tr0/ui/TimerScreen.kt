package com.example.tr0.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay

@Composable
fun Timer(
    onSecondPassed: () -> Unit,
    gameViewModel: GameViewModel = viewModel()
) {
    val gameUiState by gameViewModel.uiState.collectAsState()

    LaunchedEffect(true) { // Al crear el composable
        while (true) {
            delay(1000L) // Esperar 1 segundo
            onSecondPassed() // Llama la función para notificar el paso de un segundo
        }
    }
    
    Text(
        text = String.format("%02d:%02d", gameUiState.timer / 60, gameUiState.timer % 60),
        style = MaterialTheme.typography.titleLarge
    )
}