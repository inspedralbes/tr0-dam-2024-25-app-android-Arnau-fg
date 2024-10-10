package com.example.tr0

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tr0.ui.GameViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tr0.ui.PlayGameScreen
import com.example.tr0.ui.StartGameScreen
import com.example.tr0.ui.SummaryScreen


enum class GameScreen() {
    Start,
    Game,
    Summary
}

@Composable
fun TR0App(
    viewModel: GameViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = Modifier, //.fillMaxSize()
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = GameScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = GameScreen.Start.name) {
                StartGameScreen(
                    onPlayButtonClicked = {
                        viewModel.resetGame()
                        viewModel.getGameQuestions(navController)
                    },

                    modifier = Modifier
                        .padding(innerPadding)
                )
            }
            composable(route = GameScreen.Game.name) {
                PlayGameScreen(
                    gameViewModel = viewModel,
                    onSaveAnswer = {
                        viewModel.addAnswer(it, navController)
                    },
                    pregunta = uiState.token,
                    modifier = Modifier
                        .padding(innerPadding)
                )
            }
            composable(route = GameScreen.Summary.name) {
                SummaryScreen(
                    answers = uiState.mutableAnswers,
                    modifier = Modifier
                        .padding(innerPadding)
                )
            }
        }
    }
}