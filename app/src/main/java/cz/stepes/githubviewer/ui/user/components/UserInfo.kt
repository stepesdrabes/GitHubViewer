package cz.stepes.githubviewer.ui.user.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import cz.stepes.githubviewer.ui.shared.components.IconInfo
import cz.stepes.githubviewer.ui.shared.theme.spacing
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
            user.email?.let { IconInfo(iconId = R.drawable.ic_fi_rr_at, value = it) }
            user.company?.let { IconInfo(iconId = R.drawable.ic_fi_rr_building, value = it) }
            user.location?.let { IconInfo(iconId = R.drawable.ic_fi_rr_marker, value = it) }
            user.blog?.let { IconInfo(iconId = R.drawable.ic_fi_rr_link, value = it) }

            if (user.followers > 0) {
                IconInfo(
                    iconId = R.drawable.ic_fi_rr_following,
                    value = "${stringResource(id = R.string.user_followers)} – ${user.followers}"
                )
            }
        }
    }
}