package com.example.jokeesingleserving.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.jokeesingleserving.core.designsystem.theme.JokeeSingleServingTheme
import com.example.jokeesingleserving.features.joke.JokeRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            JokeeSingleServingTheme {
                JokeRoute()
            }
        }
    }
}