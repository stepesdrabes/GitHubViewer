package cz.stepes.githubviewer.ui.repository.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.remote.responses.CommitResponse
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.ui.repository.RepositoryViewModel
import cz.stepes.githubviewer.ui.shared.components.IconLabelButton
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import cz.stepes.githubviewer.util.Resource

@ExperimentalMaterialApi
@Composable
fun RepositoryContent(
    repository: RepositoryResponse,
    listState: LazyListState,
    viewModel: RepositoryViewModel
) {
    val commitsState = produceState<Resource<List<CommitResponse>>>(
        initialValue = Resource.Loading()
    ) {
        value = viewModel.getCommits(
            username = repository.owner.login,
            repositoryName = repository.name
        )
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding)
    ) {
        item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.large)) }

        item { RepoNameColumn(repository = repository) }

        item {
            repository.description?.let {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

                Text(
                    modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
                    text = it,
                    fontSize = MaterialTheme.textSize.normal,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

            RepoInfo(repository = repository)
        }

        item { LanguagesBar(repository = repository) }

        item {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

            IconLabelButton(
                label = stringResource(id = R.string.open_in_browser),
                iconId = R.drawable.ic_fi_rr_arrow_right,
                onClick = {
                    // TODO: Open repository in browser
                    //uriHandler.openUri(it.url)
                }
            )
        }

        item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge)) }

        // Commits
        when (commitsState.value) {
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
                commitsState.value.data?.let {
                    item {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = MaterialTheme.spacing.textOffset),
                            text = "${stringResource(id = R.string.repository_last_commits)} – ${it.size}",
                            fontSize = MaterialTheme.textSize.medium,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colors.onBackground,
                        )
                    }

                    item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium)) }

                    items(count = it.size) { index ->
                        CommitItem(commit = it[index])

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