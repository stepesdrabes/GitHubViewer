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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import cz.stepes.githubviewer.ui.shared.components.CircleCroppedImage
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize

@Composable
fun UsernameRow(
    user: UserResponse
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        CircleCroppedImage(size = 96.dp, url = user.avatarUrl)

        Column {
            user.name?.let {
                Text(
                    text = it,
                    fontSize = MaterialTheme.textSize.large,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colors.onBackground,
                )
            }

            Text(
                text = user.login,
                fontSize = MaterialTheme.textSize.normal,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface,
            )
        }
    }
}