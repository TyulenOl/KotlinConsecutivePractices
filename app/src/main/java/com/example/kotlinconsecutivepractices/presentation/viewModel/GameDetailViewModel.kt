package com.example.kotlinconsecutivepractices.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.kotlinconsecutivepractices.domain.repository.IGamesRepository
import com.example.kotlinconsecutivepractices.presentation.mappers.GamesUiMapper
import com.example.kotlinconsecutivepractices.presentation.model.GameDetailUiEntity
import com.example.kotlinconsecutivepractices.presentation.navigation.GameDetailRoute
import com.example.kotlinconsecutivepractices.presentation.state.GameDetailState

class GameDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: IGamesRepository,
    private val mapper: GamesUiMapper
) : ViewModel() {
    private val _gameDetailState = MutableGameDetailState()
    val gameDetailState = _gameDetailState as GameDetailState

    init {
        loadGameById()
    }

    private fun loadGameById() {
        val gameId = savedStateHandle.toRoute<GameDetailRoute>().gameId;
        _gameDetailState.gameDetail = mapper.mapGameDetails(repository.getGameDetailsById(gameId))
    }

    private class MutableGameDetailState: GameDetailState {
        override var gameDetail: GameDetailUiEntity? by mutableStateOf(null)
    }
}