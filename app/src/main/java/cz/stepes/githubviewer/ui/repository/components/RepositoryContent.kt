package cz.stepes.githubviewer.ui.repository.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import cz.stepes.githubviewer.data.remote.responses.CommitResponse
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.ui.repository.RepositoryViewModel
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.util.Resource

@Composable
fun RepositoryContent(
    repository: RepositoryResponse,
    listState: LazyListState,
    viewModel: RepositoryViewModel
) {
    val commitsState = produceState<Resource<List<CommitResponse>>>(
        initialValue = Resource.Loading()
    ) {
        value = viewModel.getCommits(
            username = repository.owner.login,
            repositoryName = repository.name
        )
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding)
    ) {
        item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.large)) }


    }
}