package cz.stepes.githubviewer.ui.user

import androidx.lifecycle.ViewModel
import cz.stepes.githubviewer.data.remote.GitHubService
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import cz.stepes.githubviewer.util.Resource

class UserViewModel(
    private val gitHubService: GitHubService
) : ViewModel() {

    suspend fun getUserInfo(username: String): Resource<UserResponse> {
        return gitHubService.getUser(username)
    }

    suspend fun getUserRepositories(username: String): Resource<List<RepositoryResponse>> {
        return gitHubService.getRepositories(username)
    }
}