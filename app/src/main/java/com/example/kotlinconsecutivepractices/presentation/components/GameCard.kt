package com.example.kotlinconsecutivepractices.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.kotlinconsecutivepractices.R
import com.example.kotlinconsecutivepractices.presentation.model.GameUiEntity

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GameCard(
    game: GameUiEntity,
    onClick: (Int) -> Unit,
    onFavoriteClick: (GameUiEntity) -> Unit
) {
    var isFavorite by remember { mutableStateOf(game.isFavorite) }

    Box {
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
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .width(125.dp),
                )
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(1.dp)
                ) {
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

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                imageVector = Icons.Default.Star ,
                contentDescription = null,
                tint = if (isFavorite) Color(0xFFFDCC0D) else Color.Gray,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        isFavorite = !isFavorite
                        onFavoriteClick(game)
                    }
            )
        }
    }
}