package com.example.kotlinconsecutivepractices.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kotlinconsecutivepractices.domain.model.GameEntity
import com.example.kotlinconsecutivepractices.domain.repository.IGamesRepository
import com.example.kotlinconsecutivepractices.presentation.mappers.GamesUiMapper
import com.example.kotlinconsecutivepractices.presentation.model.GameUiEntity
import com.example.kotlinconsecutivepractices.presentation.state.GamesListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GamesListViewModel(
    private val repository: IGamesRepository,
    private val mapper: GamesUiMapper
) : ViewModel() {

    private val _uiState = MutableGamesListState()
    val uiState = _uiState as GamesListState

    init {
        loadGames()
    }

    private fun loadGames() {
        _uiState.games = repository.getGames().map { mapper.mapGame(it) };
    }

    private class MutableGamesListState: GamesListState {
        override var games: List<GameUiEntity> by mutableStateOf(emptyList())
    }
}