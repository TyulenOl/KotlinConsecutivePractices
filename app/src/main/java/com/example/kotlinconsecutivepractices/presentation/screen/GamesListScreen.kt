package com.example.kotlinconsecutivepractices.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kotlinconsecutivepractices.R
import com.example.kotlinconsecutivepractices.presentation.components.FullScreenLoadingIndicator
import com.example.kotlinconsecutivepractices.presentation.components.GameCard
import com.example.kotlinconsecutivepractices.presentation.viewModel.FavoriteViewModel
import com.example.kotlinconsecutivepractices.presentation.viewModel.GamesListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GamesListScreen(
    onGameClick: (Int) -> Unit
) {
    val gameListVM = koinViewModel<GamesListViewModel>()
    val favoriteVM = koinViewModel<FavoriteViewModel>()
    val uiState = gameListVM.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 8.dp),
    ) {

        Text(
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 5.dp),
            text = stringResource(R.string.games_list)
        )

        uiState.error?.let {
            Text(
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Red,
                text = it
            )
            Button(
                onClick = { gameListVM.onReload() }
            ) {
                Text(stringResource(R.string.retry))
            }
        }

        if (uiState.loading) {
            FullScreenLoadingIndicator()
        }

        if (uiState.games.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.games, key = { it.id }) {
                    GameCard(
                        game = it,
                        onGameClick,
                        onFavoriteClick = { favoriteVM.onFavoriteClick(it) })
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    text = "Пусто"
                )
            }
        }
    }
}