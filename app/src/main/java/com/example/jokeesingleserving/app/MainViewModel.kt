package com.example.jokeesingleserving.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeesingleserving.app.MainActivityUiState.Loading
import com.example.jokeesingleserving.app.MainActivityUiState.Success
import com.example.jokeesingleserving.core.data.repository.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class MainActivityUiState { Loading, Success }

@HiltViewModel
class MainViewModel @Inject constructor(
    private val jokeRepository: JokeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            jokeRepository.preloadJoke()
            _uiState.value = Success
        }
    }
}