package cz.stepes.githubviewer.ui.user.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.user.UserViewModel
import cz.stepes.githubviewer.util.Resource
import org.koin.androidx.compose.viewModel

@ExperimentalMaterialApi
@Composable
fun UserContent(
    user: UserResponse
) {
    val viewModel: UserViewModel by viewModel()

    val repositoriesState = produceState<Resource<List<RepositoryResponse>>>(
        initialValue = Resource.Loading()
    ) {
        value = viewModel.getUserRepositories(user.login)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.pagePadding),
    ) {
        UserInfo(user = user)

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

        UserRepositories(user = user, repositories = repositoriesState.value)
    }
}