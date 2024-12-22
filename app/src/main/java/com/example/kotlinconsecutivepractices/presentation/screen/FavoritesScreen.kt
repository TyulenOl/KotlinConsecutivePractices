package com.example.kotlinconsecutivepractices.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kotlinconsecutivepractices.R
import com.example.kotlinconsecutivepractices.presentation.components.GameCard
import com.example.kotlinconsecutivepractices.presentation.viewModel.FavoriteViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoritesScreen(
    onGameClick: (Int) -> Unit,
) {
    val viewModel = koinViewModel<FavoriteViewModel>()
    val favoriteState = viewModel.favoriteState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 8.dp),
    ) {

        Text(
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 5.dp),
            text = stringResource(R.string.favorites_games)
        )

        if (favoriteState.games.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(favoriteState.games, key = { it.id }) {
                    GameCard(
                        game = it,
                        onGameClick,
                        onFavoriteClick = { viewModel.onFavoriteClick(it) })
                }
            }
        }
    }
}