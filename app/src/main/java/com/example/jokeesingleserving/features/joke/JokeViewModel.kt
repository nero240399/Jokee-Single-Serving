package com.example.jokeesingleserving.features.joke

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeesingleserving.core.data.repository.JokeRepository
import com.example.jokeesingleserving.core.model.Joke
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val jokeRepository: JokeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<Joke?>(null)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            jokeRepository.preloadJoke()
            jokeRepository.currentJoke
                .onEach { _uiState.value = it }
                .launchIn(viewModelScope)
        }
    }

    fun feedbackJoke(isLiked: Boolean) {
        viewModelScope.launch {
            jokeRepository.feedbackJoke(isLiked)
        }
    }
}