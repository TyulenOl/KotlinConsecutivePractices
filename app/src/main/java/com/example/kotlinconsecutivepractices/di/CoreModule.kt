package com.example.kotlinconsecutivepractices.di

import androidx.datastore.core.DataStore
import com.example.kotlinconsecutivepractices.data.dataSource.DataSourceProvider
import com.example.kotlinconsecutivepractices.data.mappers.GamesDataMapper
import com.example.kotlinconsecutivepractices.data.repository.GamesRepository
import com.example.kotlinconsecutivepractices.data.repository.ProfileRepository
import com.example.kotlinconsecutivepractices.domain.model.ProfileEntity
import com.example.kotlinconsecutivepractices.domain.repository.IGamesRepository
import com.example.kotlinconsecutivepractices.domain.repository.IProfileRepository
import com.example.kotlinconsecutivepractices.presentation.mappers.GamesUiMapper
import com.example.kotlinconsecutivepractices.presentation.utils.FiltersPinCache
import com.example.kotlinconsecutivepractices.presentation.viewModel.EditProfileViewModel
import com.example.kotlinconsecutivepractices.presentation.viewModel.FavoriteViewModel
import com.example.kotlinconsecutivepractices.presentation.viewModel.GameDetailViewModel
import com.example.kotlinconsecutivepractices.presentation.viewModel.GamesListViewModel
import com.example.kotlinconsecutivepractices.presentation.viewModel.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module {
    single<IGamesRepository> { GamesRepository(get(), get(), get()) }
    single<IProfileRepository> { ProfileRepository() }
    single<FiltersPinCache> { FiltersPinCache(get()) }

    factory { GamesUiMapper() }
    factory { GamesDataMapper() }
    factory<DataStore<ProfileEntity>>(named("profile")) { DataSourceProvider(get()).provider() }

    viewModel { GamesListViewModel(get(), get(), get()) }
    viewModel { GameDetailViewModel(get(), get(), get()) }
    viewModel { FavoriteViewModel(get(), get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { EditProfileViewModel(get(), get()) }
}