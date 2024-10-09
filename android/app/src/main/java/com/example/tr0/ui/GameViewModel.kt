package com.example.tr0.ui

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.tr0.GameScreen
import com.example.tr0.data.GameUiState
import com.example.tr0.data.Pregunta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    fun addAnswer(answerId: Int, navController: NavController) {
        _uiState.update { currentState ->

            val updatedAnswers = currentState.mutableAnswers

            updatedAnswers.add(answerId)

            var finished = false

            if (currentState.mutableAnswers.size == 10) {
                finished = true
                println(currentState.mutableAnswers)
                navController.navigate(GameScreen.Summary.name)
            } else {
                val length = currentState.mutableAnswers.size
                println("saving: $answerId")
                println("size is: $length")
            }
            currentState.copy(
                mutableAnswers = updatedAnswers,
                finishedGame = finished
            )
        }
    }

    private fun setQuestions(questions: List<Pregunta>) {
        _uiState.update { currentState ->
            currentState.copy(
                questions = questions
            )
        }
    }

    fun setToken(token: String) {
        _uiState.update { currentState ->
            currentState.copy(
                token = token
            )
        }
    }

    fun resetGame() {
        println("resetting")
        _uiState.update { currentState ->
            currentState.copy(
                questions = listOf(),
                finishedGame = false,
                mutableAnswers = mutableListOf()
            )
        }

        val token = uiState.value.token

        // Call for questions to back
    }
}