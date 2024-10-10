package com.example.tr0.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.tr0.GameScreen
import com.example.tr0.communication.QuestionsApi
import com.example.tr0.data.GameUiState
import com.example.tr0.data.Pregunta
import com.example.tr0.data.StartGameJSON
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException


class GameViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    fun addAnswer(answerId: Int, navController: NavController) {

        _uiState.update { currentState ->


            val updatedAnswers = currentState.mutableAnswers

            updatedAnswers.add(answerId)

            var finished = false

            var nextQuestion: Pregunta = currentState.questions.get(0)

            if (currentState.mutableAnswers.size == 10) {
                navController.navigate(GameScreen.Summary.name)
                finished = true
                println(currentState.mutableAnswers)
            } else {
                val length = currentState.mutableAnswers.size

                nextQuestion = currentState.questions.get(length)

            }
            currentState.copy(
                mutableAnswers = updatedAnswers,
                finishedGame = finished,
                preguntaActual = nextQuestion
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

    fun getGameQuestions(navController: NavHostController) {

        viewModelScope.launch {
            try {

                println("entrant")

                val listResult = QuestionsApi.retrofitService.getPreguntes(_uiState.value.token)

                println("fetch")




                _uiState.update {currentState ->
                    currentState.copy(
                        token = listResult.token,
                        questions = listResult.formattedGameQuestions,
                        preguntaActual = listResult.formattedGameQuestions.get(0)
                    )
                }

                println(uiState.value.questions)
                println(_uiState.value.questions)


                println("guardat")

                navController.navigate(GameScreen.Game.name)

                println("al joc")

            } catch (e: IOException) {
                println(e)
            }
        }
    }

    private fun postGameAnswers() {
//        Lógica aquí
    }
}