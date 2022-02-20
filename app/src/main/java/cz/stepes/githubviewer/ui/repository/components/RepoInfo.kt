package cz.stepes.githubviewer.ui.repository.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.ui.shared.components.IconInfo
import cz.stepes.githubviewer.ui.shared.theme.spacing

@Composable
fun RepoInfo(
    repository: RepositoryResponse
) {
    Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)) {
        repository.homepage?.let { IconInfo(iconId = R.drawable.ic_fi_rr_link, value = it) }

        IconInfo(
            iconId = R.drawable.ic_fi_rs_star,
            value = "${stringResource(id = R.string.repository_stars)} – ${repository.stargazersCount}"
        )

        IconInfo(
            iconId = R.drawable.ic_fork,
            value = "${stringResource(id = R.string.repository_forks)} – ${repository.forksCount}"
        )
    }
}