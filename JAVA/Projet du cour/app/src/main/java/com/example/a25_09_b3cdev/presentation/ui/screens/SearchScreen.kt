package com.example.a25_09_b3cdev.presentation.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.a25_09_b3cdev.R
import com.example.a25_09_b3cdev.data.remote.WeatherEntity
import com.example.a25_09_b3cdev.presentation.ui.theme.AppTheme
import com.example.a25_09_b3cdev.presentation.viewmodel.MainViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenPreview() {
    AppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val previewViewModel = MainViewModel()
            previewViewModel.loadFakeData()
            SearchScreen(
                    modifier = Modifier.padding(innerPadding),
                    mainViewModel = previewViewModel
            )
        }
    }
}

@Composable
fun SearchBar(
        searchText: String,
        onSearchTextChange: (String) -> Unit,
        onSearch: () -> Unit,
        modifier: Modifier = Modifier
) {
    OutlinedTextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            modifier = modifier.fillMaxWidth().padding(16.dp),
            placeholder = {
                Text(
                        text = stringResource(R.string.search_placeholder),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            leadingIcon = {
                Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_description),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            colors =
                    OutlinedTextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface
                    ),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearch() })
    )
}

@Composable
fun SearchScreen(modifier: Modifier = Modifier, mainViewModel: MainViewModel = viewModel()) {

    val list = mainViewModel.dataList.collectAsStateWithLifecycle().value
    var searchText by rememberSaveable { mutableStateOf("") }

    fun performSearch() {
        if (searchText.isNotBlank()) {
            mainViewModel.loadWeathers(searchText)
        }
    }

    Column(modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        SearchBar(
                searchText = searchText,
                onSearchTextChange = { searchText = it },
                onSearch = { performSearch() }
        )

        LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth()) {
            items(list) { item -> PictureRowItem(data = item) }
        }

        Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                    onClick = { searchText = "" },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors =
                            ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
            ) {
                Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.clear_filter_description),
                        modifier = Modifier.padding(end = 8.dp)
                )
                Text(stringResource(R.string.clear_filter))
            }

            Button(
                    onClick = { performSearch() },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors =
                            ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
            ) {
                Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = stringResource(R.string.reload_data_description),
                        modifier = Modifier.padding(end = 8.dp)
                )
                Text(stringResource(R.string.reload_data))
            }
        }
    }
}

@Composable // Composable affichant 1 élément
fun PictureRowItem(modifier: Modifier = Modifier, data: WeatherEntity) {
    var isExpanded by remember { mutableStateOf(false) }

    Row(
            modifier =
                    modifier.padding(10.dp)
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                            .clickable { isExpanded = !isExpanded }
                            .animateContentSize()
    ) {

        // Permission Internet nécessaire
        AsyncImage(
                model = data.weather.firstOrNull()?.icon,
                contentDescription = stringResource(R.string.picture_of_cat),
                contentScale = ContentScale.FillWidth,

                // Image d'attente.
                error = painterResource(R.drawable.error),
                onError = { println(it) },
                modifier = Modifier.heightIn(max = 100.dp).widthIn(max = 100.dp)
        )

        Column(modifier = Modifier.padding(10.dp).weight(1f)) {
            Text(text = data.name, fontSize = 20.sp, color = MaterialTheme.colorScheme.primary)
            Text(
                    text = data.getResume(),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
