package cz.stepes.githubviewer.ui.repository.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.remote.responses.BranchResponse
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.ui.shared.components.IconInfo
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import cz.stepes.githubviewer.util.Resource

@Composable
fun BranchesSheet(
    repository: RepositoryResponse?,
    branchesState: State<Resource<List<BranchResponse>>>
) {
    Box(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding)
        ) {
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

                Text(
                    modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
                    text = stringResource(id = R.string.repository_branches),
                    fontSize = MaterialTheme.textSize.medium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colors.onBackground
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            }

            when (branchesState.value) {
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

                is Resource.Success -> branchesState.value.data?.let { branches ->
                    items(items = branches) {
                        Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)) {
                            IconInfo(iconId = R.drawable.ic_branch, value = it.name)

                            if (repository?.defaultBranch == it.name) {
                                Text(
                                    text = stringResource(id = R.string.repository_default_branch),
                                    fontSize = MaterialTheme.textSize.normal,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.onSurface
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}