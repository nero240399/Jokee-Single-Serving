package com.example.jokeesingleserving.features.joke.components

import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun JokeButton(
    @StringRes textId: Int,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    Button(
        shape = JokeButtonDefaults.ButtonShape,
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = stringResource(id = textId))
    }
}

object JokeButtonDefaults {
    val ButtonShape = RoundedCornerShape(10)
}