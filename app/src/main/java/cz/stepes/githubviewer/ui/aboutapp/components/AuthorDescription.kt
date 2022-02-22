package cz.stepes.githubviewer.ui.aboutapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.ui.shared.components.CircularImage
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize

@Composable
fun AuthorDescription() {
    Column {
        Text(
            modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
            text = stringResource(id = R.string.about_app_author_info),
            fontSize = MaterialTheme.textSize.medium,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colors.onBackground
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            CircularImage(
                size = 48.dp,
                url = "https://avatars.githubusercontent.com/u/74901420?v=4"
            )

            Text(
                text = stringResource(id = R.string.about_app_author_name),
                fontSize = MaterialTheme.textSize.normal,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colors.onBackground,
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        Text(
            modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
            text = stringResource(id = R.string.about_app_author_description),
            fontSize = MaterialTheme.textSize.normal,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface,
        )
    }
}