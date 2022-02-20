package cz.stepes.githubviewer.ui.repository.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.remote.HttpRoutes
import cz.stepes.githubviewer.data.remote.responses.BranchResponse
import cz.stepes.githubviewer.data.remote.responses.CommitResponse
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.ui.shared.components.IconLabelButton
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import cz.stepes.githubviewer.util.Resource

@ExperimentalMaterialApi
@Composable
fun RepositoryContent(
    repository: RepositoryResponse,
    listState: LazyListState,
    branches: Resource<List<BranchResponse>>,
    languages: Resource<Map<String, Int>>,
    commits: Resource<List<CommitResponse>>,
) {
    val uriHandler = LocalUriHandler.current

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding)
    ) {

        // Column with owner and repo name
        item {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

            RepoNameColumn(repository = repository)
        }

        // Description
        repository.description?.let {
            item {
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

        // Icon information
        item {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

            RepoInfo(repository = repository, branchesState = branches)
        }

        // Languages
        if (repository.language != null) {
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

                Text(
                    modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
                    text = stringResource(id = R.string.repository_languages),
                    fontSize = MaterialTheme.textSize.medium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colors.onBackground
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                when (languages) {
                    is Resource.Loading -> Box(modifier = Modifier.fillMaxWidth()) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.Center),
                            strokeWidth = 2.dp,
                        )
                    }

                    is Resource.Error -> Text(text = stringResource(id = R.string.user_loading_error))

                    is Resource.Success -> languages.data?.let { LanguagesBar(languages = it) }
                }
            }
        }

        // Open in browser button
        item {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

            IconLabelButton(
                label = stringResource(id = R.string.open_in_browser),
                iconId = R.drawable.ic_fi_rr_arrow_right,
                onClick = {
                    uriHandler.openUri("${HttpRoutes.GITHUB_BASE_URL}/${repository.owner.login}/${repository.name}")
                }
            )
        }

        // Current branch
        item {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

            when (branches) {
                is Resource.Loading -> Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center),
                        strokeWidth = 2.dp,
                    )
                }

                is Resource.Error -> Text(text = stringResource(id = R.string.user_loading_error))

                is Resource.Success -> {
                    branches.data?.let {
                        CurrentBranch(
                            repository = repository,
                            currentBranch = repository.defaultBranch
                        )
                    }
                }
            }
        }

        // Last commits title
        item {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
                text = stringResource(id = R.string.repository_last_commits),
                fontSize = MaterialTheme.textSize.medium,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colors.onBackground,
            )
        }

        // Commits
        when (commits) {
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
                commits.data?.let {
                    item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium)) }

                    items(count = if (it.size < 10) it.size else 10) { index ->
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