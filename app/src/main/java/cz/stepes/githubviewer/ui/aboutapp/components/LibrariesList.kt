package cz.stepes.githubviewer.ui.aboutapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize

@Composable
fun LibrariesList() {
    val names = stringArrayResource(id = R.array.about_app_libraries_names)
    val description = stringArrayResource(id = R.array.about_app_libraries_description)

    Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)) {
        Text(
            modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
            text = stringResource(id = R.string.about_app_used_libraries),
            fontSize = MaterialTheme.textSize.medium,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colors.onBackground
        )

        Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)) {
            names.forEachIndexed { index, item ->
                Library(name = item, description = description[index])
            }
        }
    }
}

@Composable
private fun Library(
    name: String,
    description: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.onBackground)
        )

        Text(
            text = name,
            fontSize = MaterialTheme.textSize.normal,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground
        )

        Text(
            text = description,
            fontSize = MaterialTheme.textSize.normal,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface
        )
    }
}