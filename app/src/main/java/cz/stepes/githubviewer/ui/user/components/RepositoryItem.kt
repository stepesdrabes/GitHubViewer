package cz.stepes.githubviewer.ui.user.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.ui.shared.components.CircularImage
import cz.stepes.githubviewer.ui.shared.components.IconInfo
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import cz.stepes.githubviewer.util.LanguageColors

@ExperimentalMaterialApi
@Composable
fun RepositoryItem(
    repository: RepositoryResponse,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = 0.dp,
        onClick = onClick,
    ) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.large)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularImage(size = 24.dp, url = repository.owner.avatarUrl)

                Text(
                    text = repository.owner.login,
                    fontSize = MaterialTheme.textSize.normal,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
                text = repository.name,
                fontSize = MaterialTheme.textSize.medium,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colors.onBackground
            )

            repository.description?.let {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                Text(
                    modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
                    text = it,
                    fontSize = MaterialTheme.textSize.normal,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
            }

            if (repository.language != null || repository.fork) {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            }

            Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)) {
                if (repository.fork) {
                    IconInfo(
                        iconId = R.drawable.ic_fork,
                        value = stringResource(id = R.string.repository_fork)
                    )
                }

                repository.language?.let {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val color = LanguageColors.getLanguageColor(repository.language)

                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(color = color)
                        )

                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                        Text(
                            modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
                            text = it,
                            fontSize = MaterialTheme.textSize.medium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }
            }
        }
    }
}