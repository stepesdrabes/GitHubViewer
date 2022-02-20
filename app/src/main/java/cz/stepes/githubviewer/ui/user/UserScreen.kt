package cz.stepes.githubviewer.ui.user

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import cz.stepes.githubviewer.ui.shared.components.CircularImage
import cz.stepes.githubviewer.ui.shared.components.IconLabelButton
import cz.stepes.githubviewer.ui.shared.components.NoConnection
import cz.stepes.githubviewer.ui.shared.components.NotFound
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import cz.stepes.githubviewer.ui.user.components.RepositoryItem
import cz.stepes.githubviewer.ui.user.components.UserInfo
import cz.stepes.githubviewer.ui.user.components.UsernameRow
import cz.stepes.githubviewer.util.Resource
import cz.stepes.githubviewer.util.ResourceErrorState
import org.koin.androidx.compose.viewModel

@ExperimentalMaterialApi
@Destination
@Composable
fun UserScreen(
    navigator: DestinationsNavigator,
    username: String
) {
    val viewModel: UserViewModel by viewModel()

    val userState = produceState<Resource<UserResponse>>(
        initialValue = Resource.Loading()
    ) {
        value = viewModel.getUserInfo(username)
    }

    val repositoriesState = produceState<Resource<List<RepositoryResponse>>>(
        initialValue = Resource.Loading()
    ) {
        value = viewModel.getUserRepositories(username)
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
                                visible = listState.firstVisibleItemIndex <= 1 || userState.value.data == null
                            ) {
                                Text(
                                    text = stringResource(id = R.string.back),
                                    fontSize = MaterialTheme.textSize.medium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colors.onBackground,
                                )
                            }

                            userState.value.data?.let {
                                AnimatedVisibility(
                                    enter = expandVertically(expandFrom = Alignment.Top),
                                    exit = shrinkVertically(shrinkTowards = Alignment.Top),
                                    visible = listState.firstVisibleItemIndex > 1
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        CircularImage(size = 24.dp, url = it.avatarUrl)

                                        Column {
                                            it.name?.let {
                                                Text(
                                                    text = it,
                                                    fontSize = MaterialTheme.textSize.normal,
                                                    fontWeight = FontWeight.ExtraBold,
                                                    color = MaterialTheme.colors.onBackground,
                                                )
                                            }

                                            Text(
                                                text = it.login,
                                                fontSize = MaterialTheme.textSize.small,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colors.onSurface,
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
        when (userState.value) {
            is Resource.Loading -> Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center),
                    strokeWidth = 2.dp
                )
            }

            is Resource.Error -> userState.value.errorState?.let {
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

            is Resource.Success -> userState.value.data?.let {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding)
                ) {
                    item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.large)) }

                    // User info
                    item {
                        UsernameRow(user = it)
                    }

                    it.bio?.let {
                        item {
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
                    }

                    // Icons with labels
                    item { UserInfo(user = it) }

                    item {
                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

                        IconLabelButton(
                            label = stringResource(id = R.string.open_in_browser),
                            iconId = R.drawable.ic_fi_rr_arrow_right,
                            onClick = {
                                //uriHandler.openUri(it.url)
                            }
                        )
                    }

                    item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge)) }

                    // Repositories title
                    item {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = MaterialTheme.spacing.textOffset),
                            text = "${stringResource(id = R.string.user_repositories)} – ${it.publicRepos}",
                            fontSize = MaterialTheme.textSize.medium,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colors.onBackground,
                        )
                    }

                    item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium)) }

                    // Repositories
                    when (repositoriesState.value) {
                        is Resource.Loading -> item {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .align(Alignment.Center),
                                    strokeWidth = 2.dp,
                                )
                            }
                        }

                        is Resource.Error -> item { Text(text = stringResource(id = R.string.user_loading_error)) }

                        is Resource.Success -> {
                            repositoriesState.value.data?.let {
                                items(count = it.size) { index ->
                                    RepositoryItem(repository = it[index])

                                    if (index < it.size - 1) {
                                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                                    }
                                }

                                item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.large)) }
                            }
                        }
                    }
                }
            }
        }
    }
}