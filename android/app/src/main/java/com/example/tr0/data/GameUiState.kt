package com.example.tr0.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class GameUiState (
    var questions: List<Pregunta> = listOf(),
    val mutableAnswers: MutableList<Int> = mutableListOf(),
    var token: String = "",
    val allJsonInfo: StartGameJSON = StartGameJSON("",  listOf<Pregunta>()),
    val finishedGame: Boolean = false,
    var preguntaActual: Pregunta = Pregunta(0,"", listOf()),
    var score: ReturnAnswers = ReturnAnswers(false,Score(0,0)),
    var timer: Int = 0,
)

@Serializable
data class StartGameJSON (

    @SerialName (value = "token")
    val token: String,

    @SerialName (value = "formattedGameQuestions")
    val formattedGameQuestions: List<Pregunta>
)

@Serializable
data class Pregunta(

    @SerialName (value = "id")
    val id: Int,

    @SerialName (value = "pregunta")
    val pregunta: String,

    @SerialName (value = "respostes")
    val respostes: List<Resposta>
)

@Serializable
data class Resposta(

    @SerialName (value = "id")
    val id: Int,

    @SerialName (value = "resposta")
    val resposta: String,

    @SerialName (value = "imatge")
    val imatge: String
)

data class SendAnswers(
    val token: String,
    val answers: MutableList<Int>
)

@Serializable
data class ReturnAnswers(
    @SerialName (value = "valid")
    val valid: Boolean,

    @SerialName (value = "playerScore")
    val playerScore: Score,
)

@Serializable
data class Score(

    @SerialName (value = "encertades")
    val encertades: Int,

    @SerialName (value = "totals")
    val totals: Int
)