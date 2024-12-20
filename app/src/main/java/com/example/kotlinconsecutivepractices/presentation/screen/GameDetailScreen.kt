package com.example.kotlinconsecutivepractices.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.kotlinconsecutivepractices.R
import com.example.kotlinconsecutivepractices.presentation.viewModel.GameDetailViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GameDetailScreen() {
    val viewModel = koinViewModel<GameDetailViewModel>()
    val gameDetail = viewModel.gameDetailState.gameDetail

    if (gameDetail != null) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        text = gameDetail.name
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            text = gameDetail.rating
                        )
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .height(20.dp)
                                .padding(bottom = 1.dp)
                        )
                    }
                }

                AsyncImage(
                    model = gameDetail.backgroundImage,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth(),
                    alignment = Alignment.Center
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ){
                ValueWithTitleText("${stringResource(R.string.publisher)}: ", gameDetail.publishers)
                ValueWithTitleText("${stringResource(R.string.developer)}: ", gameDetail.developers)
                ValueWithTitleText("${stringResource(R.string.date_release)}: ", gameDetail.released)
                ValueWithTitleText("${stringResource(R.string.playtime)}: ", gameDetail.playtime)
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    text = "${stringResource(R.string.about)}:"
                )

                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    text = gameDetail.description
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    text = "${stringResource(R.string.tags)}:"
                )

                FlowRowLabels(gameDetail.genres)
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    text = "${stringResource(R.string.platforms)}:"
                )

                FlowRowLabels(gameDetail.platforms)
            }
        }
    }
}

@Composable
fun ValueWithTitleText(title: String, value: String) {
    Row {
        Text(
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            text = title
        )
        Text(
            style = MaterialTheme.typography.bodyLarge,
            text = value
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowLabels(values: List<String>) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        for (platform in values) {
            Card(
                shape = RoundedCornerShape(6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    modifier = Modifier.padding(6.dp, 2.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    text = platform
                )
            }
        }
    }
}