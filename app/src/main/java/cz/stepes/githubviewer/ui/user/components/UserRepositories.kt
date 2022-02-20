package cz.stepes.githubviewer.ui.user.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize
import cz.stepes.githubviewer.util.Resource

@ExperimentalMaterialApi
@Composable
fun UserRepositories(
    user: UserResponse,
    repositories: Resource<List<RepositoryResponse>>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MaterialTheme.spacing.textOffset),
            text = "${stringResource(id = R.string.user_repositories)} – ${user.publicRepos}",
            fontSize = MaterialTheme.textSize.medium,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colors.onBackground,
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        when (repositories) {
            is Resource.Loading -> CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterHorizontally),
                strokeWidth = 2.dp,
            )

            is Resource.Error -> Text(text = stringResource(id = R.string.user_loading_error))

            is Resource.Success -> {
                repositories.data?.let {
                    LazyColumn {
                        items(count = it.size) { index ->
                            RepositoryItem(repository = it[index])

                            if (index < it.size - 1) {
                                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                            }
                        }
                    }
                }
            }
        }
    }
}