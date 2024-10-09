package com.example.tr0.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SummaryScreen (
    answers: List<Int>,
    modifier: Modifier = Modifier
) {
    Column {
        answers.forEach { it ->
            Text(
                text = it.toString(),
                modifier = modifier.padding(5.dp)
            )
        }
    }
}