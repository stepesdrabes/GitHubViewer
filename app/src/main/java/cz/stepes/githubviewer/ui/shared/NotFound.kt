package cz.stepes.githubviewer.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.ui.theme.spacing
import cz.stepes.githubviewer.ui.theme.textSize

@Composable
fun NotFound(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.extraLarge),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.error_404),
            color = MaterialTheme.colors.onBackground,
            fontSize = 80.sp,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(id = R.string.not_found),
            color = MaterialTheme.colors.onSurface,
            fontSize = MaterialTheme.textSize.normal,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}