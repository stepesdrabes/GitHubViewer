package cz.stepes.githubviewer.ui.search

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.viewModel

@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: SearchViewModel by viewModel()


}