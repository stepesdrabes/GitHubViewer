package cz.stepes.githubviewer.ui.repository.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import cz.stepes.githubviewer.util.LanguageColors
import kotlin.math.roundToInt

@Composable
fun LanguagesBar(
    languages: Map<String, Int>
) {
    val totalSize = languages.values.sum()
    val orderedLanguages = languages.toSortedMap(compareByDescending { languages[it] })

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .clip(MaterialTheme.shapes.medium)
        ) {
            orderedLanguages.forEach { language ->
                val color = LanguageColors.getLanguageColor(language.key)
                    ?: MaterialTheme.colors.primary

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(language.value.toFloat() / totalSize.toFloat())
                        .background(color)
                )
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

        FlowRow(
            mainAxisSpacing = MaterialTheme.spacing.extraLarge,
            crossAxisSpacing = MaterialTheme.spacing.small
        ) {
            orderedLanguages.forEach {
                val color = LanguageColors.getLanguageColor(it.key)
                    ?: MaterialTheme.colors.primary

                val percentage = (it.value.toDouble() / totalSize.toDouble() * 100).roundToInt()

                Row(
                    modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(color = color)
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                    Text(
                        modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
                        text = it.key,
                        fontSize = MaterialTheme.textSize.normal,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onBackground
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))

                    Text(
                        modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
                        text = "$percentage %",
                        fontSize = MaterialTheme.textSize.normal,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }
        }
    }
}