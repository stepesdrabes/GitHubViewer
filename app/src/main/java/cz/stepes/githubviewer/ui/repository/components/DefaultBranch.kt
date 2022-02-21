package cz.stepes.githubviewer.ui.repository.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.ui.shared.components.IconInfo
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize

@Composable
fun CurrentBranch(
    repository: RepositoryResponse,
) {
    Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)) {
        Divider(color = MaterialTheme.colors.surface, thickness = 1.dp)

        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.textOffset)
        ) {
            IconInfo(iconId = R.drawable.ic_branch, value = repository.defaultBranch)

            Text(
                text = stringResource(id = R.string.repository_default_branch),
                fontSize = MaterialTheme.textSize.normal,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface
            )

            Spacer(modifier = Modifier.weight(1.0f))

            Text(
                modifier = Modifier.clickable {

                },
                text = stringResource(id = R.string.repository_view_branches),
                fontSize = MaterialTheme.textSize.normal,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colors.primary
            )
        }

        Divider(color = MaterialTheme.colors.surface, thickness = 1.dp)
    }
}