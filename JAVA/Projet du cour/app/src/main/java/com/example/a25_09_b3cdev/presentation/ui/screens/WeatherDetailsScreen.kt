package com.example.a25_09_b3cdev.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.a25_09_b3cdev.R
import com.example.a25_09_b3cdev.data.remote.DescriptionEntity
import com.example.a25_09_b3cdev.data.remote.WeatherEntity
import com.example.a25_09_b3cdev.presentation.viewmodel.MainViewModel

@Composable
fun WeatherDetailsScreen(
        modifier: Modifier = Modifier,
        mainViewModel: MainViewModel,
        onBack: () -> Unit
) {
    val selected = mainViewModel.selectedWeather.collectAsStateWithLifecycle().value

    if (selected == null) {
        Box(
                modifier =
                        modifier
                                .fillMaxSize()
                                .padding(16.dp),
                contentAlignment = Alignment.Center
        ) {
            Text(stringResource(R.string.details_empty))
        }
    } else {
        DetailsContent(
                modifier =
                        modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState()),
                weather = selected,
                onBack = onBack
        )
    }
}

@Composable
private fun DetailsContent(
    modifier: Modifier = Modifier,
    weather: WeatherEntity,
    onBack: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Card {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = weather.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text(text = weather.getResume())
                Text(
                    text = stringResource(R.string.temperature_format, weather.main.temp),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(R.string.wind_format, weather.wind.speed),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Card {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.conditions_title),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                weather.weather.forEach { condition ->
                    ConditionRow(condition = condition)
                }
            }
        }

        // Bouton retour additionnel (la TopAppBar globale contient déjà la flèche Retour)
        androidx.compose.material3.Button(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.back))
        }
    }
}

@Composable
private fun ConditionRow(condition: DescriptionEntity) {
    Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        AsyncImage(
                model = condition.icon,
                contentDescription = condition.description,
                modifier = Modifier.size(56.dp)
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(
                    text = condition.description,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
            )
        }
    }
}

