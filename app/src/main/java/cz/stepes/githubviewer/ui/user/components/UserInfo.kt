package cz.stepes.githubviewer.ui.user.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import cz.stepes.githubviewer.util.StringUtil

@Composable
fun UserInfo(
    user: UserResponse
) {
    Column(modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset)) {
        if (StringUtil.isSomeValid(
                user.email,
                user.company,
                user.location,
                user.blog
            ) || user.followers > 0
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        }

        Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)) {
            user.email?.let { IconText(iconId = R.drawable.ic_fi_rr_at, value = it) }
            user.company?.let { IconText(iconId = R.drawable.ic_fi_rr_building, value = it) }
            user.location?.let { IconText(iconId = R.drawable.ic_fi_rr_marker, value = it) }
            user.blog?.let { IconText(iconId = R.drawable.ic_fi_rr_link, value = it) }

            if (user.followers > 0) {
                IconText(
                    iconId = R.drawable.ic_fi_rr_following,
                    value = "${stringResource(id = R.string.user_followers)} – ${user.followers}"
                )
            }
        }
    }
}

@Composable
fun IconText(
    iconId: Int,
    value: String,
    onClick: (() -> Unit)? = null
) {
    if (StringUtil.isNotValid(value)) {
        return
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconId),
            tint = MaterialTheme.colors.onSurface,
            contentDescription = null
        )

        Text(
            text = value,
            fontSize = MaterialTheme.textSize.normal,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground,
        )
    }
}