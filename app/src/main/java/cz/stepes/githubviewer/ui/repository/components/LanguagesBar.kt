package cz.stepes.githubviewer.ui.repository.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.ui.repository.RepositoryViewModel
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import cz.stepes.githubviewer.ui.shared.theme.unimportantText
import cz.stepes.githubviewer.util.LanguageColors
import cz.stepes.githubviewer.util.Resource
import org.koin.androidx.compose.viewModel
import kotlin.math.roundToInt

@Composable
fun LanguagesBar(
    repository: RepositoryResponse
) {
    val viewModel: RepositoryViewModel by viewModel()

    repository.language?.let { _ ->
        val languagesState = produceState<Resource<Map<String, Int>>>(
            initialValue = Resource.Loading()
        ) {
            value = viewModel.getLanguages(
                username = repository.owner.login,
                repositoryName = repository.name
            )
        }

        languagesState.value.data?.let { languages ->
            val totalSize = languages.values.sum()
            val orderedLanguages = languages.toSortedMap(compareByDescending { languages[it] })

            Column {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

                Text(
                    modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
                    text = stringResource(id = R.string.repository_languages),
                    fontSize = MaterialTheme.textSize.medium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colors.onBackground
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .clip(MaterialTheme.shapes.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    orderedLanguages.forEach { language ->
                        val color = LanguageColors.getLanguageColor(language.key)

                        color?.let {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(language.value.toFloat() / totalSize.toFloat())
                                    .background(it)
                            )
                        }

                    }
                }

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

                orderedLanguages.forEach {
                    val color = LanguageColors.getLanguageColor(it.key)
                    val percentage = (it.value.toDouble() / totalSize.toDouble() * 100).roundToInt()

                    Row(modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset)) {
                        color?.let { color ->
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(color = color)
                            )

                            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                        }

                        Text(
                            modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
                            text = it.key,
                            fontSize = MaterialTheme.textSize.normal,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onSurface
                        )

                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))

                        Text(
                            modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
                            text = "$percentage %",
                            fontSize = MaterialTheme.textSize.normal,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.unimportantText
                        )
                    }
                }
            }
        }
    }
}