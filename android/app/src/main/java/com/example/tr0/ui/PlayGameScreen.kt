package com.example.tr0.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tr0.ui.theme.TR0Theme
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun PlayGameScreen(
    gameViewModel: GameViewModel = viewModel(),
    onSaveAnswer: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    pregunta: String
) {
    val gameUiState by gameViewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xff87b5ff), Color(0xff52bdfa))
                )
            ),
    ) {
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = modifier
                    .padding(20.dp)
                    .background(Color(0xff0b2b96)),

            ) {
                Text(
//                    text = "Aquesta és la pregunta que has d'encertar, bona sort",
                    text = gameUiState.preguntaActual.pregunta,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = modifier
                        .padding(30.dp),
                    fontSize = 20.sp
                    )
            }
            Spacer(modifier = modifier)
            Row (
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { onSaveAnswer(gameUiState.preguntaActual.respostes.get(0).id) },
                    modifier = modifier
                        .height(150.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff3f67eb),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = gameUiState.preguntaActual.respostes.get(0).resposta)
//                    Text(text = "hola")
                }
                Button(
                    onClick = { onSaveAnswer(gameUiState.preguntaActual.respostes.get(1).id) },
                    modifier = modifier,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff3f67eb),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = gameUiState.preguntaActual.respostes.get(1).resposta)
//                    Text(text = "hola")
                }
            }
            Row (
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { onSaveAnswer(gameUiState.preguntaActual.respostes.get(2).id) },
                    modifier = modifier,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff3f67eb),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = gameUiState.preguntaActual.respostes.get(2).resposta)
//                    Text(text = "hola")
                }
                Button(
                    onClick = { onSaveAnswer(gameUiState.preguntaActual.respostes.get(3).id) },
                    modifier = modifier,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff3f67eb),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = gameUiState.preguntaActual.respostes.get(3).resposta)
//                    Text(text = "hola")
                }
            }
        }
    }
}

@Preview
@Composable
fun PlayGamePreview () {
    TR0Theme {
        PlayGameScreen( pregunta = "Aquesta és la pregunta que has d'encertar, bona sort")
    }
}