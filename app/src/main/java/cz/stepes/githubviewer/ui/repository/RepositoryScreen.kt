package cz.stepes.githubviewer.ui.repository

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.ui.repository.components.RepositoryContent
import cz.stepes.githubviewer.ui.shared.components.NoConnection
import cz.stepes.githubviewer.ui.shared.components.NotFound
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import cz.stepes.githubviewer.util.Resource
import cz.stepes.githubviewer.util.ResourceErrorState
import org.koin.androidx.compose.viewModel as viewModel1

@Destination
@Composable
fun RepositoryScreen(
    navigator: DestinationsNavigator,
    username: String,
    repositoryName: String
) {
    val viewModel: RepositoryViewModel by viewModel1()

    val repositoryState = produceState<Resource<RepositoryResponse>>(
        initialValue = Resource.Loading()
    ) {
        value = viewModel.getRepository(username, repositoryName)
    }

    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding / 2),
                elevation = 0.dp,
                content = {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .clip(MaterialTheme.shapes.medium)
                            .clickable {
                                navigator.navigateUp()
                            }
                            .padding(horizontal = MaterialTheme.spacing.pagePadding / 2),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_fi_br_arrow_left),
                            tint = MaterialTheme.colors.onBackground,
                            contentDescription = stringResource(id = R.string.back)
                        )

                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.large))

                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            AnimatedVisibility(
                                enter = expandVertically(expandFrom = Alignment.Bottom),
                                exit = shrinkVertically(shrinkTowards = Alignment.Bottom),
                                visible = listState.firstVisibleItemIndex <= 1 || repositoryState.value.data == null
                            ) {
                                Text(
                                    text = stringResource(id = R.string.back),
                                    fontSize = MaterialTheme.textSize.medium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colors.onBackground,
                                )
                            }

                            repositoryState.value.data?.let {
                                AnimatedVisibility(
                                    enter = expandVertically(expandFrom = Alignment.Top),
                                    exit = shrinkVertically(shrinkTowards = Alignment.Top),
                                    visible = listState.firstVisibleItemIndex > 1
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Text(
                                                text = it.owner.login,
                                                fontSize = MaterialTheme.textSize.small,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colors.onSurface,
                                            )

                                            Text(
                                                text = it.name,
                                                fontSize = MaterialTheme.textSize.normal,
                                                fontWeight = FontWeight.ExtraBold,
                                                color = MaterialTheme.colors.onBackground,
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            )
        },
    ) {
        when (repositoryState.value) {
            is Resource.Loading -> Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center),
                    strokeWidth = 2.dp
                )
            }

            is Resource.Error -> repositoryState.value.errorState?.let {
                Box(modifier = Modifier.fillMaxSize()) {
                    when (it) {
                        ResourceErrorState.NotFound -> NotFound(
                            modifier = Modifier.align(
                                Alignment.Center
                            )
                        )
                        ResourceErrorState.NetworkError -> NoConnection(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            is Resource.Success -> repositoryState.value.data?.let {
                RepositoryContent(
                    repository = it,
                    listState = listState,
                    viewModel = viewModel
                )
            }
        }
    }
}