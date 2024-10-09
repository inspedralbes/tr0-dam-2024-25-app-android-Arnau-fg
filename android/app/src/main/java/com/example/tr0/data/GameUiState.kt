package com.example.tr0.data

data class GameUiState (
    val questions: List<Pregunta> = listOf(),
    val mutableAnswers: MutableList<Int> = mutableListOf(),
    val token: String = "",
    val finishedGame: Boolean = false
)

data class Pregunta(
    val id: Int,
    val titulo: String,
    val respuestas: List<Respuesta>  // Lista de respuestas
)

data class Respuesta(
    val id: Int,
    val imagen: String,  // Ruta o URL de la imagen
    val texto: String
)