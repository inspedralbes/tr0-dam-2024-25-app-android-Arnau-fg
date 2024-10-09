package com.example.tr0.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tr0.R
import com.example.tr0.ui.theme.TR0Theme

@Composable
fun StartGameScreen(
    onPlayButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
//            .background(Color(0xff5496ff))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xff5496ff), Color(0xff073c91))
                )
            ),
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onPlayButtonClicked,
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff011475),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(R.string.comencar_partida),
                    fontSize = 24.sp,
                )
            }

        }
    }


}

@Preview
@Composable
fun StartPagePreview() {
    TR0Theme {
        StartGameScreen(onPlayButtonClicked = { /*TODO*/ } )
    }
}