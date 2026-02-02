package com.example.a25_09_b3cdev.presentation.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
// @Preview(showBackground = true, showSystemUi = true,
//           uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES or
// android.content.res.Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun SearchScreenPreview() {
    // Il faut remplacer NomVotreAppliTheme par le thème de votre application
    // Utilisé par exemple dans MainActivity.kt sous setContent {...}
    AppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            SearchScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun SearchScreen(modifier: Modifier = Modifier, mainViewModel: MainViewModel = viewModel()) {

    val list = mainViewModel.dataList.collectAsStateWithLifecycle().value
    var searchText by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        // SearchBar
        OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                placeholder = {
                    Text(
                            text = "Votre recherche ici",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                leadingIcon = {
                    Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Rechercher",
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
                singleLine = true
        )

        // LazyColumn pour la liste
        LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth()) {
            items(list) { item -> PictureRowItem(data = item) }
        }

        // Boutons en bas
        Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                    onClick = { /* Non fonctionnel pour le moment */},
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
                        contentDescription = "Clear filter",
                        modifier = Modifier.padding(end = 8.dp)
                )
                Text("Clear filter")
            }

            Button(
                    onClick = { /* Non fonctionnel pour le moment */},
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
                        contentDescription = "Reload data",
                        modifier = Modifier.padding(end = 8.dp)
                )
                Text("Reload data")
            }
        }
    }
}

@Composable // Composable affichant 1 élément
fun PictureRowItem(modifier: Modifier = Modifier, data: WeatherEntity) {
    Row(
            modifier =
                    modifier.padding(10.dp)
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
    ) {

        // Permission Internet nécessaire
        AsyncImage(
                model = data.weather.firstOrNull()?.icon,
                // Pour aller le chercher dans string.xml R de votre package com.nom.projet
                // contentDescription = getString(R.string.picture_of_cat),
                // En dur
                contentDescription = "une photo de chat",
                contentScale = ContentScale.FillWidth,

                // Pour toto.png. Si besoin de choisir l'import pour la classe R, c'est celle de
                // votre package
                // Image d'échec de chargement qui sera utilisé par la preview
                error = painterResource(R.drawable.error),
                // Image d'attente.
                // placeholder = painterResource(R.drawable.toto),

                onError = { println(it) },
                modifier = Modifier.heightIn(max = 100.dp).widthIn(max = 100.dp)
        )

        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = data.name, fontSize = 20.sp, color = MaterialTheme.colorScheme.primary)
            Text(
                    text = data.getResume().take(15) + "...",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
