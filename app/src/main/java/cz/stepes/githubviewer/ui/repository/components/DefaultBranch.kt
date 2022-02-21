package cz.stepes.githubviewer.ui.repository.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.ui.shared.components.IconInfo
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun CurrentBranch(
    repository: RepositoryResponse,
    bottomSheetScaffoldState: BottomSheetScaffoldState
) {
    val coroutineScope = rememberCoroutineScope()

    Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)) {
        Divider(color = MaterialTheme.colors.surface, thickness = 1.dp)

        Row(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.textOffset),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconInfo(iconId = R.drawable.ic_branch, value = repository.defaultBranch)

            Text(
                text = stringResource(id = R.string.repository_default_branch),
                fontSize = MaterialTheme.textSize.normal,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface
            )

            Spacer(modifier = Modifier.weight(1.0f))

            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable {
                        coroutineScope.launch {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        }
                    },
                text = stringResource(id = R.string.repository_view_branches),
                fontSize = MaterialTheme.textSize.normal,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colors.primary
            )
        }

        Divider(color = MaterialTheme.colors.surface, thickness = 1.dp)
    }
}