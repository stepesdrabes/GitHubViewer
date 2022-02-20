package cz.stepes.githubviewer.ui.repository.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.ui.shared.components.CircularImage
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize

@Composable
fun RepoNameColumn(
    repository: RepositoryResponse
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            CircularImage(size = 32.dp, url = repository.owner.avatarUrl)

            Text(
                text = repository.owner.login,
                fontSize = MaterialTheme.textSize.medium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        Text(
            modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
            text = repository.name,
            fontSize = MaterialTheme.textSize.large,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colors.onBackground
        )
    }
}