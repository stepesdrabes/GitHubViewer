package cz.stepes.githubviewer.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.ui.destinations.UserScreenDestination
import cz.stepes.githubviewer.ui.search.components.SearchBar
import cz.stepes.githubviewer.ui.search.components.StartSearching
import cz.stepes.githubviewer.ui.shared.theme.spacing

@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator
) {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.pagePadding)
    ) {
        SearchBar(
            placeholder = stringResource(id = R.string.search_placeholder),
            //enabled = !viewModel.isLoading.value,
            onSearch = {
                if (it.isNotEmpty()) {
                    //focusManager.clearFocus()
                    navigator.navigate(UserScreenDestination(username = it))
                }
            }
        )

        StartSearching(modifier = Modifier.align(Alignment.Center))
    }
}