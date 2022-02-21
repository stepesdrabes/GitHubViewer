package cz.stepes.githubviewer.ui.user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.stepes.githubviewer.data.remote.GitHubService
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import cz.stepes.githubviewer.util.Resource
import kotlinx.coroutines.launch

class UserViewModel(
    private val gitHubService: GitHubService
) : ViewModel() {

    private var currentUser: String? = null

    val userState = mutableStateOf<Resource<UserResponse>>(Resource.Loading())
    val repositoriesState = mutableStateOf<Resource<List<RepositoryResponse>>>(Resource.Loading())

    fun loadUserInfo(username: String) {
        if (currentUser != username) {

            currentUser = username;

            viewModelScope.launch {
                userState.value = gitHubService.getUser(username)
                repositoriesState.value = gitHubService.getRepositoriesList(username)
            }
        }
    }
}