package cz.stepes.githubviewer.ui.repository.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.ui.repository.RepositoryViewModel
import cz.stepes.githubviewer.ui.shared.components.IconInfo
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import cz.stepes.githubviewer.util.Resource
import kotlinx.coroutines.launch
import org.koin.androidx.compose.viewModel

@ExperimentalMaterialApi
@Composable
fun BranchesContent(
    repository: RepositoryResponse
) {
    val viewModel: RepositoryViewModel by viewModel()

    val coroutineScope = rememberCoroutineScope()
    val shownState = remember { mutableStateOf(false) }

    Column {
        Divider(color = MaterialTheme.colors.surface, thickness = 1.dp)

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

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

            viewModel.branchesState.value.data?.let {
                if (it.size > 1) {
                    Spacer(modifier = Modifier.weight(1.0f))

                    Text(
                        modifier = Modifier
                            .fillMaxHeight()
                            .clickable {
                                coroutineScope.launch {
                                    shownState.value = !shownState.value
                                }
                            },
                        text = if (shownState.value)
                            stringResource(id = R.string.repository_hide_branches)
                        else
                            stringResource(id = R.string.repository_show_branches),
                        fontSize = MaterialTheme.textSize.normal,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colors.primary
                    )
                }
            }
        }

        AnimatedVisibility(visible = shownState.value) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        }

        AnimatedVisibility(visible = shownState.value) {
            when (viewModel.branchesState.value) {
                is Resource.Loading -> CircularProgressIndicator(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterHorizontally),
                    strokeWidth = 2.dp
                )

                is Resource.Error -> Text(text = stringResource(id = R.string.user_loading_error))

                is Resource.Success -> Column(
                    verticalArrangement = Arrangement.spacedBy(
                        MaterialTheme.spacing.small
                    )
                ) {
                    viewModel.branchesState.value.data?.let { branches ->
                        Column(
                            modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
                        ) {
                            branches.forEach {
                                IconInfo(iconId = R.drawable.ic_branch, value = it.name)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        Divider(color = MaterialTheme.colors.surface, thickness = 1.dp)
    }
}