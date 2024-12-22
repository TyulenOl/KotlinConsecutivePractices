package com.example.kotlinconsecutivepractices.di

import com.example.kotlinconsecutivepractices.data.mappers.GamesDataMapper
import com.example.kotlinconsecutivepractices.data.repository.GamesRepository
import com.example.kotlinconsecutivepractices.domain.repository.IGamesRepository
import com.example.kotlinconsecutivepractices.presentation.mappers.GamesUiMapper
import com.example.kotlinconsecutivepractices.presentation.utils.FiltersPinCache
import com.example.kotlinconsecutivepractices.presentation.viewModel.FavoriteViewModel
import com.example.kotlinconsecutivepractices.presentation.viewModel.GameDetailViewModel
import com.example.kotlinconsecutivepractices.presentation.viewModel.GamesListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single<IGamesRepository> { GamesRepository(get(), get(), get()) }
    single<FiltersPinCache> { FiltersPinCache(get()) }

    factory { GamesUiMapper() }
    factory { GamesDataMapper() }

    viewModel { GamesListViewModel(get(), get(), get()) }
    viewModel { GameDetailViewModel(get(), get(), get()) }
    viewModel { FavoriteViewModel(get(), get()) }
}