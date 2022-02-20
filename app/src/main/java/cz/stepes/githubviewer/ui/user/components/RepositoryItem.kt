package cz.stepes.githubviewer.ui.user.components

import androidx.compose.foundation.background
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
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.ui.shared.components.CircularImage
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import cz.stepes.githubviewer.util.LanguageColors

@ExperimentalMaterialApi
@Composable
fun RepositoryItem(
    repository: RepositoryResponse
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = 0.dp,
        onClick = {

        },
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

            repository.language?.let {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    val color = LanguageColors.getLanguageColor(repository.language)

                    color?.let {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .background(color = it, shape = MaterialTheme.shapes.medium)
                        )

                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                    }

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