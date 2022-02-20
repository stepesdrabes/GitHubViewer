package cz.stepes.githubviewer.ui.user.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import cz.stepes.githubviewer.ui.shared.components.CircularImage
import cz.stepes.githubviewer.ui.shared.theme.Nunito
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize

@Composable
fun UsernameRow(
    user: UserResponse
) {
    val titleTextStyle = TextStyle(
        fontFamily = Nunito,
        fontSize = MaterialTheme.textSize.large,
        fontWeight = FontWeight.ExtraBold,
        color = MaterialTheme.colors.onBackground
    )

    val subtitleTextStyle = TextStyle(
        fontFamily = Nunito,
        fontSize = MaterialTheme.textSize.medium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.onSurface,
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        CircularImage(size = 96.dp, url = user.avatarUrl)

        Column {
            user.name?.let {
                Text(
                    text = it,
                    style = titleTextStyle
                )
            }

            Text(
                text = user.login,
                style = if (user.name == null) titleTextStyle else subtitleTextStyle
            )
        }
    }
}