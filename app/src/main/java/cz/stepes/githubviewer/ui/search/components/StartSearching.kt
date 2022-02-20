package cz.stepes.githubviewer.ui.search.components

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize

@Composable
fun StartSearching(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.pagePadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraLarge)
    ) {
        Icon(
            modifier = Modifier.size(96.dp),
            painter = painterResource(id = R.drawable.ic_search),
            tint = MaterialTheme.colors.onBackground,
            contentDescription = stringResource(id = R.string.app_name)
        )

        Text(
            text = stringResource(id = R.string.search_description),
            color = MaterialTheme.colors.onSurface,
            fontSize = MaterialTheme.textSize.normal,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}