package cz.stepes.githubviewer.ui.repository.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import cz.stepes.githubviewer.util.LanguageColors
import java.text.DecimalFormat

@Composable
fun LanguagesInfo(
    languages: Map<String, Int>
) {
    val totalSize = remember { languages.values.sum() }
    val orderedLanguages = remember { languages.toSortedMap(compareByDescending { languages[it] }) }

    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
    ) {
        LanguagesBar(totalSize, orderedLanguages)

        // Individual languages
        FlowRow(
            mainAxisSpacing = MaterialTheme.spacing.extraLarge,
            crossAxisSpacing = MaterialTheme.spacing.small
        ) {
            orderedLanguages.forEach {
                val languageColor = LanguageColors.getLanguageColor(it.key)

                val decimalFormat = DecimalFormat("#.#")
                val percentage = decimalFormat.format(
                    it.value.toDouble() / totalSize.toDouble() * 100
                )

                if (it.value.toDouble() / totalSize.toDouble() * 100 >= 0.1) {
                    Row(
                        modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(color = languageColor)
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
}

@Composable
fun LanguagesBar(
    totalSize: Int,
    orderedLanguages: Map<String, Int>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(16.dp)
            .clip(MaterialTheme.shapes.medium)
    ) {
        orderedLanguages.forEach { language ->
            val color = LanguageColors.getLanguageColor(language.key)

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(language.value.toFloat() / totalSize.toFloat())
                    .background(color)
            )
        }
    }
}