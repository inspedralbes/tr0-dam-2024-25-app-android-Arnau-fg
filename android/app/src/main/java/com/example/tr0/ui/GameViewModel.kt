package com.example.tr0.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.tr0.GameScreen
import com.example.tr0.communication.QuestionsApi
import com.example.tr0.data.GameUiState
import com.example.tr0.data.Pregunta
import com.example.tr0.data.ReturnAnswers
import com.example.tr0.data.Score
import com.example.tr0.data.SendAnswers
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

                viewModelScope.launch {
                    try {
                        val endGameStats = QuestionsApi.retrofitService.postAnswers(SendAnswers(currentState.token,currentState.mutableAnswers))

                        _uiState.update { thisValue ->
                            thisValue.copy(
                                score = endGameStats,
                            )
                        }

                        println(endGameStats)
                        navController.navigate(GameScreen.Summary.name)
                        finished = true
                    } catch (e: IOException) {
                        println(e)
                    }
                }
            } else {
                var length = currentState.mutableAnswers.size

                if(length>10) {
                    length = 0
                }

                nextQuestion = currentState.questions.get(length)

            }
            currentState.copy(
                mutableAnswers = updatedAnswers,
                finishedGame = finished,
                preguntaActual = nextQuestion,
            )
        }
    }

    fun addSecondToTimer() {
        _uiState.update { thisValue ->
            val newTime = thisValue.timer + 1

            println(newTime)

            thisValue.copy(
                timer = newTime,
            )
        }
    }



    fun resetGame() {
        _uiState.update { currentState ->
            currentState.copy(
                questions = listOf(),
                finishedGame = false,
                mutableAnswers = mutableListOf(),
                timer = 0,
                score = ReturnAnswers(false,Score(0,0)),

            )
        }
    }

    fun getGameQuestions(navController: NavHostController) {

        viewModelScope.launch {
            try {

//                val listResult = QuestionsApi.retrofitService.getPreguntes(_uiState.value.token)
                val listResult = QuestionsApi.retrofitService.getPreguntes()

                _uiState.update {currentState ->
                    currentState.copy(
                        token = listResult.token,
                        questions = listResult.formattedGameQuestions,
                        preguntaActual = listResult.formattedGameQuestions.get(0)
                    )
                }


                navController.navigate(GameScreen.Game.name)


            } catch (e: IOException) {
                println(e)
            }
        }
    }
}