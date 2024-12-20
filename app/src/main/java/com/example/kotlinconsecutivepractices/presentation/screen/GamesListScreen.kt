package com.example.kotlinconsecutivepractices.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.kotlinconsecutivepractices.R
import com.example.kotlinconsecutivepractices.presentation.extensions.fadingEdge
import com.example.kotlinconsecutivepractices.presentation.model.GameUiEntity
import com.example.kotlinconsecutivepractices.presentation.viewModel.GamesListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GamesListScreen(
    onGameClick: (Int) -> Unit
) {
    val viewModel = koinViewModel<GamesListViewModel>()
    val uiState = viewModel.uiState;

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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.games) {
                GameElement(game = it, onGameClick)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GameElement(game: GameUiEntity, onClick: (Int) -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onClick(game.id) }
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp, 10.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AsyncImage(
                model = game.backgroundImage,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .width(130.dp),
                alignment = Alignment.Center
            )
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(1.dp)
            )
            {
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = game.name
                )

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                    maxLines = 2
                ) {
                    for (genre in game.genres) {
                        Card(
                            shape = RoundedCornerShape(6.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        ) {
                            Text(
                                modifier = Modifier.padding(6.dp, 2.dp),
                                style = MaterialTheme.typography.labelMedium,
                                text = genre
                            )
                        }
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Text(
                            style = MaterialTheme.typography.bodyMedium,
                            text = game.rating
                        )
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .height(15.dp)
                                .padding(bottom = 1.dp)
                        )
                    }

                    Text(
                        style = MaterialTheme.typography.bodyMedium,
                        text = "${stringResource(R.string.date_release)}: ${game.released}"
                    )
                }
            }
        }
    }
}