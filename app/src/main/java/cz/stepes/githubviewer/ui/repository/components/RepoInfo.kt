package cz.stepes.githubviewer.ui.repository.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.remote.responses.BranchResponse
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.ui.shared.components.IconInfo
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.util.Resource

@Composable
fun RepoInfo(
    repository: RepositoryResponse,
    branchesState: Resource<List<BranchResponse>>
) {
    Column(
        modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        repository.homepage?.let { IconInfo(iconId = R.drawable.ic_fi_rr_link, value = it) }

        IconInfo(
            iconId = R.drawable.ic_fi_rr_eye,
            value = "${stringResource(id = R.string.repository_watchers)} – ${repository.watchersCount}"
        )

        IconInfo(
            iconId = R.drawable.ic_fi_rs_star,
            value = "${stringResource(id = R.string.repository_stars)} – ${repository.stargazersCount}"
        )

        branchesState.data?.let {
            Row {
                IconInfo(
                    iconId = R.drawable.ic_fork,
                    value = "${stringResource(id = R.string.repository_forks)} – ${repository.forksCount}"
                )

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraLarge))

                IconInfo(
                    iconId = R.drawable.ic_branch,
                    value = "${stringResource(id = R.string.repository_branches)} – ${it.size}"
                )
            }
        }
    }
}