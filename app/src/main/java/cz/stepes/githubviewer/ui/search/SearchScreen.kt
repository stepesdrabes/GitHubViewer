package cz.stepes.githubviewer.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.ui.destinations.UserScreenDestination
import cz.stepes.githubviewer.ui.search.components.SearchBar
import cz.stepes.githubviewer.ui.search.components.StartSearching
import cz.stepes.githubviewer.ui.shared.theme.spacing

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.pagePadding)
    ) {
        SearchBar(
            placeholder = stringResource(id = R.string.search_placeholder),
            textState = textState,
            onSearch = {
                if (it.isNotEmpty()) {
                    navigator.navigate(UserScreenDestination(username = it))
                }
            }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f)
        ) {
            StartSearching(modifier = Modifier.align(Alignment.Center))
        }
    }
}