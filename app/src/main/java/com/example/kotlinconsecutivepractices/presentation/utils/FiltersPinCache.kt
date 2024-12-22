package com.example.kotlinconsecutivepractices.presentation.utils

import com.example.kotlinconsecutivepractices.presentation.viewModel.GamesListViewModel

class FiltersPinCache(
    private val viewModel: GamesListViewModel
) {
    fun isFiltersUsed(): Boolean {
        return viewModel.uiState.isFiltersUsed
    }
}