package cz.stepes.githubviewer.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
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

    }
}