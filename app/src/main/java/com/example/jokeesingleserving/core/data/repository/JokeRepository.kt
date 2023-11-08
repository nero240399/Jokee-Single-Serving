package com.example.jokeesingleserving.core.data.repository

import com.example.jokeesingleserving.core.model.Joke
import kotlinx.coroutines.flow.StateFlow

interface JokeRepository {

    val currentJoke: StateFlow<Joke?>

    suspend fun feedbackJoke(isLiked: Boolean)

    // Simulate loading joke is long-running task requiring preloading
    suspend fun preloadJoke()
}