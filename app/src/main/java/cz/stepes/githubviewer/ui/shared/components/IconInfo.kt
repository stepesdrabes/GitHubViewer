package cz.stepes.githubviewer.ui.shared.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import cz.stepes.githubviewer.util.StringUtil

@Composable
fun IconInfo(
    iconId: Int,
    value: String
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

        LinkifyText(
            text = value,
            fontSize = MaterialTheme.textSize.normal,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground,
            textOverflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}