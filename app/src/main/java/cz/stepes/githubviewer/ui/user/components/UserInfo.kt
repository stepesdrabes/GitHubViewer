package cz.stepes.githubviewer.ui.user.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import cz.stepes.githubviewer.ui.shared.components.IconLabelButton
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import cz.stepes.githubviewer.R

@Composable
fun UserInfo(
    user: UserResponse
) {
    val uriHandler = LocalUriHandler.current

    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

        UsernameRow(user = user)

        user.bio?.let {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = MaterialTheme.spacing.textOffset,
                        end = MaterialTheme.spacing.pagePadding
                    ),
                text = it,
                fontSize = MaterialTheme.textSize.normal,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface,
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

        IconLabelButton(
            label = stringResource(id = R.string.open_in_browser),
            iconId = R.drawable.ic_fi_rr_arrow_right,
            onClick = {
                uriHandler.openUri(user.url)
            }
        )
    }
}