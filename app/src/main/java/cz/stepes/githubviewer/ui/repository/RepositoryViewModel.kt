package cz.stepes.githubviewer.ui.repository

import androidx.lifecycle.ViewModel
import cz.stepes.githubviewer.data.remote.GitHubService
import cz.stepes.githubviewer.data.remote.responses.CommitResponse
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.util.Resource

class RepositoryViewModel(
    private val gitHubService: GitHubService
) : ViewModel() {

    suspend fun getRepository(
        username: String,
        repositoryName: String
    ): Resource<RepositoryResponse> {
        return gitHubService.getRepository(username, repositoryName)
    }

    suspend fun getCommits(
        username: String,
        repositoryName: String
    ): Resource<List<CommitResponse>> {
        return gitHubService.getCommits(username, repositoryName)
    }
}