package cz.stepes.githubviewer.ui.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import cz.stepes.githubviewer.ui.shared.components.NotFound
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import cz.stepes.githubviewer.ui.user.components.UserInfo
import cz.stepes.githubviewer.util.Resource
import org.koin.androidx.compose.viewModel

@Destination
@Composable
fun UserScreen(
    navigator: DestinationsNavigator,
    username: String
) {
    val viewModel: UserViewModel by viewModel()

    val userState = produceState<Resource<UserResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getUserInfo(username)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding),
                elevation = 0.dp,
                content = {
                    Row(
                        modifier = Modifier.clickable {
                            navigator.navigateUp()
                        },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_fi_br_arrow_left),
                            tint = MaterialTheme.colors.onBackground,
                            contentDescription = stringResource(id = R.string.back)
                        )

                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.large))

                        Text(
                            text = stringResource(id = R.string.back),
                            fontSize = MaterialTheme.textSize.medium,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colors.onBackground,
                        )
                    }
                }
            )
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (userState is Resource.Loading<*>) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center),
                    strokeWidth = 2.dp
                )

                return@Box
            }

            if (userState is Resource.Error<*>) {
                NotFound(modifier = Modifier.align(Alignment.Center))

                return@Box
            }

            userState.value.data?.let {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.pagePadding),
                    content = {
                        item {
                            UserInfo(user = it)
                        }
                    }
                )
            }
        }
    }
}