package com.example.kotlinconsecutivepractices.di

import com.example.kotlinconsecutivepractices.data.repository.TestGamesRepositoryImpl
import com.example.kotlinconsecutivepractices.domain.repository.IGamesRepository
import com.example.kotlinconsecutivepractices.presentation.mappers.GamesUiMapper
import com.example.kotlinconsecutivepractices.presentation.viewModel.GameDetailViewModel
import com.example.kotlinconsecutivepractices.presentation.viewModel.GamesListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single<IGamesRepository> { TestGamesRepositoryImpl() }

    factory { GamesUiMapper() }

    viewModel { GamesListViewModel(get(), get()) }
    viewModel { GameDetailViewModel(get(), get(), get()) }
}