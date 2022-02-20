package cz.stepes.githubviewer.ui.repository.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepes.githubviewer.data.remote.responses.CommitResponse
import cz.stepes.githubviewer.ui.shared.components.CircularImage
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import cz.stepes.githubviewer.ui.shared.theme.unimportantText

@ExperimentalMaterialApi
@Composable
fun CommitItem(
    commit: CommitResponse
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = 0.dp
    ) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.large)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                commit.author?.let {
                    CircularImage(size = 24.dp, url = it.avatarUrl)

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                }

                Text(
                    // Offset username if there's no avatar available
                    modifier = Modifier.padding(start = if (commit.author == null) MaterialTheme.spacing.textOffset else 0.dp),
                    text = commit.author?.login ?: commit.commit.author.name,
                    fontSize = MaterialTheme.textSize.normal,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
            }

            commit.commit.message?.let {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

                Text(
                    modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
                    text = it,
                    fontSize = MaterialTheme.textSize.medium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colors.onBackground
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
                text = commit.sha.substring(0, 8),
                fontSize = MaterialTheme.textSize.small,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colors.unimportantText
            )
        }
    }
}