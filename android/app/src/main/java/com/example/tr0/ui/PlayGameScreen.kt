package com.example.tr0.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PlayGameScreen(
    onSaveAnswer: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column {
        Row {
            Button(
                onClick = { onSaveAnswer(0) },
                modifier = modifier.widthIn(min = 250.dp)
            ) {
                Text(text = "Resposta 1")
            }
            Button(
                onClick = { onSaveAnswer(1) },
                modifier = modifier.widthIn(min = 250.dp)
            ) {
                Text(text = "Resposta 2")
            }
        }
        Row {
            Button(
                onClick = { onSaveAnswer(2) },
                modifier = modifier.widthIn(min = 250.dp)
            ) {
                Text(text = "Resposta 3")
            }
            Button(
                onClick = { onSaveAnswer(3) },
                modifier = modifier.widthIn(min = 250.dp)
            ) {
                Text(text = "Resposta 4")
            }
        }
    }

}