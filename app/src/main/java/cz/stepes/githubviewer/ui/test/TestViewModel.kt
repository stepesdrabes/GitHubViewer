package cz.stepes.githubviewer.ui.test

import androidx.compose.runtime.mutableStateOf
import cz.stepes.githubviewer.data.remote.GitHubService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import kotlinx.coroutines.launch

class TestViewModel(
    private val gitHubService: GitHubService
) : ViewModel() {

    /*sealed class UIState {
        object Loading : UIState()
        data class Success(val categoryList: List<Category>) : UIState()
        object Error : UIState()
    }*/

    val user = mutableStateOf<UserResponse?>(null)
    val repositories = mutableStateOf<List<RepositoryResponse>?>(null)
    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)

    init {
        loadUser("stepesdrabes")
    }

    fun loadUser(username: String) {
        viewModelScope.launch {
            isLoading.value = true
            user.value = null
            errorMessage.value = null

            val result = gitHubService.getUser(username = username)

            isLoading.value = false

            if (result.isSuccess) {
                user.value = result.getOrNull()

                return@launch
            }
        }
    }

    fun loadRepos(username: String) {
        viewModelScope.launch {

        }
    }
}