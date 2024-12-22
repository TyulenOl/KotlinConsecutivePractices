package com.example.kotlinconsecutivepractices.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinconsecutivepractices.domain.repository.IGamesRepository
import com.example.kotlinconsecutivepractices.presentation.mappers.GamesUiMapper
import com.example.kotlinconsecutivepractices.presentation.model.GameUiEntity
import com.example.kotlinconsecutivepractices.presentation.state.FavoriteState
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: IGamesRepository,
    private val mapper: GamesUiMapper
) : ViewModel() {
    private val _favoriteState = MutableFavoriteState()
    val favoriteState = _favoriteState as FavoriteState

    init {
        loadFavoriteGames()
    }

    fun onFavoriteClick(game: GameUiEntity) {
        viewModelScope.launch {
            if (game.isFavorite)
                repository.removeGame(mapper.mapGameUiEntityToGameEntity(game))
            else
                repository.saveGame(mapper.mapGameUiEntityToGameEntity(game))

            loadFavoriteGames()
        }
    }

    private fun loadFavoriteGames() {
        viewModelScope.launch {
            _favoriteState.games = emptyList()
            _favoriteState.games = repository.getSavedGames().map { mapper.mapGameEntityToGameUiEntity(it) }
        }
    }

    private class MutableFavoriteState: FavoriteState {
        override var games: List<GameUiEntity> by mutableStateOf(emptyList())
    }
}