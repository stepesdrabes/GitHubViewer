package cz.stepes.githubviewer.ui.user.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.remote.HttpRoutes
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import cz.stepes.githubviewer.ui.destinations.RepositoryScreenDestination
import cz.stepes.githubviewer.ui.shared.components.IconLabelButton
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import cz.stepes.githubviewer.ui.user.UserViewModel
import cz.stepes.githubviewer.util.Resource

@ExperimentalMaterialApi
@Composable
fun UserContent(
    navigator: DestinationsNavigator,
    user: UserResponse,
    listState: LazyListState,
    viewModel: UserViewModel
) {
    val repositoriesState = produceState<Resource<List<RepositoryResponse>>>(
        initialValue = Resource.Loading()
    ) {
        value = viewModel.getUserRepositories(user.login)
    }

    val uriHandler = LocalUriHandler.current

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding)
    ) {
        // Row with user information
        item {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

            UsernameRow(user = user)
        }

        // Biography
        user.bio?.let {
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = MaterialTheme.spacing.textOffset,
                            end = MaterialTheme.spacing.pagePadding
                        ),
                    text = it,
                    fontSize = MaterialTheme.textSize.normal,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface,
                )
            }
        }

        // Icons with labels
        item { UserInfo(user = user) }

        // Open in browser button
        item {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

            IconLabelButton(
                label = stringResource(id = R.string.open_in_browser),
                iconId = R.drawable.ic_fi_rr_arrow_right,
                onClick = {
                    uriHandler.openUri("${HttpRoutes.GITHUB_BASE_URL}/${user.login}")
                }
            )
        }

        // Repositories title
        item {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MaterialTheme.spacing.textOffset),
                text = "${stringResource(id = R.string.user_repositories)} – ${user.publicRepos}",
                fontSize = MaterialTheme.textSize.medium,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colors.onBackground,
            )
        }

        item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium)) }

        // Repositories
        when (repositoriesState.value) {
            is Resource.Loading -> item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center),
                        strokeWidth = 2.dp,
                    )
                }
            }

            is Resource.Error -> item { Text(text = stringResource(id = R.string.user_loading_error)) }

            is Resource.Success -> {
                repositoriesState.value.data?.let {
                    items(count = it.size) { index ->
                        val repository = it[index]

                        RepositoryItem(
                            repository = repository,
                            onClick = {
                                navigator.navigate(
                                    RepositoryScreenDestination(
                                        username = repository.owner.login,
                                        repositoryName = repository.name
                                    )
                                )
                            }
                        )

                        if (index < it.size - 1) {
                            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                        }
                    }

                    item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.large)) }
                }
            }
        }
    }
}