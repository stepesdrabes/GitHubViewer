package cz.stepes.githubviewer.ui.shared.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize

@Composable
fun ErrorCode(
    errorCode: Int,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = MaterialTheme.spacing.pagePadding,
                end = MaterialTheme.spacing.pagePadding,
                bottom = MaterialTheme.spacing.pagePadding
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorCode.toString(),
            color = MaterialTheme.colors.onBackground,
            fontSize = 72.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            text = description,
            color = MaterialTheme.colors.onSurface,
            fontSize = MaterialTheme.textSize.normal,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}