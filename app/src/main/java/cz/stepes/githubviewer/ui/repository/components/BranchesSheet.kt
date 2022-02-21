package cz.stepes.githubviewer.ui.repository.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepes.githubviewer.ui.repository.RepositoryViewModel
import cz.stepes.githubviewer.ui.shared.components.IconInfo
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.util.Resource
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.ui.shared.theme.textSize

@Composable
fun BranchesSheet(
    viewModel: RepositoryViewModel
) {
    Column(modifier = Modifier.padding(MaterialTheme.spacing.pagePadding)) {
        Text(
            text = stringResource(id = R.string.repository_branches),
            fontSize = MaterialTheme.textSize.medium,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colors.onBackground,
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        when (viewModel.branchesState.value) {
            is Resource.Loading -> CircularProgressIndicator(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally),
                strokeWidth = 2.dp
            )

            is Resource.Error -> Text(text = stringResource(id = R.string.user_loading_error))

            is Resource.Success -> Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)) {
                viewModel.branchesState.value.data?.let { branches ->
                    branches.forEach {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconInfo(iconId = R.drawable.ic_branch, value = it.name)

                            Spacer(modifier = Modifier.weight(1.0f))

                            if (viewModel.repositoryState.value.data?.defaultBranch == it.name) {
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