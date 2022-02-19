package cz.stepes.githubviewer.ui.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import cz.stepes.githubviewer.ui.theme.spacing
import cz.stepes.githubviewer.ui.theme.textSize
import org.koin.androidx.compose.viewModel

@Composable
fun TestScreen() {
    val viewModel: TestViewModel by viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding),
                elevation = 0.dp,
                content = {
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
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.pagePadding),
                content = {
                    item {
                        Text(
                            text = stringResource(id = R.string.landing_text),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onSurface
                        )
                    }

                    items(count = 100) {
                        if (viewModel.isLoading.value) {
                            CircularProgressIndicator()
                            return@items
                        }

                        viewModel.user.value?.let { UserCard(user = it) }
                    }
                }
            )
        }
    )
}

@Composable
private fun UserCard(
    user: UserResponse
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = 0.dp,
        content = {
            Column(modifier = Modifier.padding(MaterialTheme.spacing.large)) {
                Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)) {
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = rememberImagePainter(
                            data = user.avatarUrl,
                            builder = {
                                transformations(CircleCropTransformation())
                            }
                        ),
                        contentDescription = null
                    )

                    Text(
                        text = user.login,
                        fontSize = MaterialTheme.textSize.normal,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    )
}