package com.example.kotlinconsecutivepractices.presentation.viewModel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinconsecutivepractices.domain.repository.IGamesRepository
import com.example.kotlinconsecutivepractices.presentation.mappers.GamesUiMapper
import com.example.kotlinconsecutivepractices.presentation.model.FiltersPreferencesData
import com.example.kotlinconsecutivepractices.presentation.model.GameUiEntity
import com.example.kotlinconsecutivepractices.presentation.state.GamesListState
import com.example.kotlinconsecutivepractices.presentation.utils.launchLoadingAndError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "filters")

class GamesListViewModel(
    private val repository: IGamesRepository,
    private val mapper: GamesUiMapper,
    private val context: Context
) : ViewModel() {

    private val _uiState = MutableGamesListState()
    val uiState = _uiState as GamesListState

    private val NAME_KEY = stringPreferencesKey("name")
    private val YEAR_KEY = stringPreferencesKey("year")
    private val FILTERS_USED_KEY = booleanPreferencesKey("withFilters")
    private val filtersDataFlow: Flow<FiltersPreferencesData> = context.dataStore.data
        .map { preferences ->
            FiltersPreferencesData(
                preferences[NAME_KEY] ?: "",
                preferences[YEAR_KEY] ?: "",
                preferences[FILTERS_USED_KEY] ?: false
            )
        }

    init {
        viewModelScope.launch {
            filtersDataFlow.collect {
                setFilters(it.nameFilter, it.yearFilter, it.isFiltersUsed)

                loadGames()
            }
        }
    }

    fun onReload() {
        loadGames()
    }

    fun updateFilters(nameFilter: String, yearFilter: String) {
        val isFiltersUsed = nameFilter.isNotEmpty() || yearFilter.isNotEmpty()

        viewModelScope.launch {
            saveFilters(nameFilter, yearFilter, isFiltersUsed)
        }

        setFilters(nameFilter, yearFilter, isFiltersUsed)
    }

    private fun loadGames() {
        viewModelScope.launchLoadingAndError(
            handleError = { _uiState.error = it.localizedMessage },
            updateLoading = { _uiState.loading = it }
        ) {
            _uiState.games = emptyList()
            _uiState.error = null

            _uiState.games = repository.getGames(
                uiState.nameFilter,
                uiState.yearFilter
            ).map { mapper.mapGameEntityToGameUiEntity(it) }
        }
    }

    fun setNameFilter(name: String) {
        _uiState.nameFilter = name
    }

    fun setYearFilter(year: String) {
        _uiState.yearFilter = year
    }

    fun setFilters(name: String, year: String, isFiltersUsed: Boolean) {
        _uiState.nameFilter = name
        _uiState.yearFilter = year
        _uiState.isFiltersUsed = isFiltersUsed
    }

    private suspend fun saveFilters(name: String, year: String, isFiltersUsed: Boolean) {
        context.dataStore.edit { filters ->
            filters[NAME_KEY] = name
            filters[YEAR_KEY] = year
            filters[FILTERS_USED_KEY] = isFiltersUsed
        }
    }

    private class MutableGamesListState : GamesListState {
        override var games: List<GameUiEntity> by mutableStateOf(emptyList())
        override var nameFilter: String by mutableStateOf("")
        override var yearFilter: String by mutableStateOf("")
        override var isFiltersUsed: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
        override var loading: Boolean by mutableStateOf(false)
    }
}