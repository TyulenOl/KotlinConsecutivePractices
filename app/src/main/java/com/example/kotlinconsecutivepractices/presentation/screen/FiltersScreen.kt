package com.example.kotlinconsecutivepractices.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.kotlinconsecutivepractices.R
import com.example.kotlinconsecutivepractices.presentation.viewModel.GamesListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FiltersScreen() {
    val viewModel = koinViewModel<GamesListViewModel>()
    val uiState = viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 8.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            text = stringResource(R.string.filters)
        )

        Column(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Column {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    text = stringResource(R.string.search_name)
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.nameFilter,
                    onValueChange = { viewModel.setNameFilter(it) },
                    placeholder = { Text(stringResource(R.string.search_name_placeholder)) }
                )
            }

            Column {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    text = stringResource(R.string.year_release)
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    value = uiState.yearFilter,
                    onValueChange = { viewModel.setYearFilter(it) },
                    placeholder = { Text(stringResource(R.string.year_release_placeholder)) }
                )
            }
        }

        Button(
            onClick = { viewModel.updateFilters(uiState.nameFilter, uiState.yearFilter) },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(
                text = stringResource(R.string.apply)
            )
        }
    }
}