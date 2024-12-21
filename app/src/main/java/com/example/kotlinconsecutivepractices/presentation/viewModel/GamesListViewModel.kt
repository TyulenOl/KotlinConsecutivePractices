package com.example.kotlinconsecutivepractices.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinconsecutivepractices.domain.model.GameEntity
import com.example.kotlinconsecutivepractices.domain.repository.IGamesRepository
import com.example.kotlinconsecutivepractices.presentation.mappers.GamesUiMapper
import com.example.kotlinconsecutivepractices.presentation.model.GameUiEntity
import com.example.kotlinconsecutivepractices.presentation.state.GamesListState
import com.example.kotlinconsecutivepractices.presentation.utils.launchLoadingAndError
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GamesListViewModel(
    private val repository: IGamesRepository,
    private val mapper: GamesUiMapper
) : ViewModel() {

    private val _uiState = MutableGamesListState()
    val uiState = _uiState as GamesListState

    init {
        loadGames()
    }

    fun onReload() {
        loadGames()
    }

    private fun loadGames() {
        viewModelScope.launchLoadingAndError(
            handleError = { _uiState.error = it.localizedMessage },
            updateLoading = { _uiState.loading = it }
        ) {
            _uiState.games = emptyList()
            _uiState.error = null

            _uiState.games = repository.getGames().map { mapper.mapGame(it) }
        }
    }

    private class MutableGamesListState: GamesListState {
        override var games: List<GameUiEntity> by mutableStateOf(emptyList())
        override var error: String? by mutableStateOf(null)
        override var loading: Boolean by mutableStateOf(false)
    }
}