package com.example.tr0.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tr0.R
import com.example.tr0.ui.theme.TR0Theme

@Composable
fun SummaryScreen (
    gameViewModel: GameViewModel = viewModel(),
    answers: List<Int>,
    modifier: Modifier = Modifier,
    onRestartClicked: () -> Unit = {}
) {
    val gameUiState by gameViewModel.uiState.collectAsState()
    Box(
    modifier = modifier
        .fillMaxSize()
        .background(
            brush = Brush.linearGradient(
                colors = listOf(Color(0xff5496ff), Color(0xff073c91))
            )
        ),
){
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = modifier,
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {
                val mediumPadding = 16.dp

                Column(
                    verticalArrangement = Arrangement.spacedBy(mediumPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(mediumPadding)
                ) {
                    Text(
                        text = stringResource(R.string.puntuacio),
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = ("${gameUiState.score.playerScore.encertades}/${gameUiState.score.playerScore.totals}"),
                        textAlign = TextAlign.Center,
                        fontSize = 40.sp,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            Card(

            ){
                Text(
                    text = String.format("%02d:%02d", gameUiState.timer / 60, gameUiState.timer % 60),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(30.dp)
                )
            }
            Button(
                onClick = onRestartClicked,
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff011475),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(R.string.reiniciar_partida),
                    fontSize = 24.sp,
                )
            }
        }

    }

}

@Preview
@Composable
fun SummaryPreview () {
    TR0Theme {
        SummaryScreen(answers = listOf(0,1,2,0,3,0,1,0))
    }
}