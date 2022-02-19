package cz.stepes.githubviewer.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.ui.search.components.SearchBar
import cz.stepes.githubviewer.ui.search.components.StartSearching
import cz.stepes.githubviewer.ui.theme.spacing
import org.koin.androidx.compose.viewModel

@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: SearchViewModel by viewModel()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.pagePadding)
    ) {
        SearchBar(
            placeholder = stringResource(id = R.string.search_placeholder),
            onSearch = {
                viewModel.searchUser(it)
            }
        )

        if (viewModel.isLoading.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center),
                strokeWidth = 2.dp
            )

            return@Box
        }

        StartSearching(modifier = Modifier.align(Alignment.Center))
    }
}