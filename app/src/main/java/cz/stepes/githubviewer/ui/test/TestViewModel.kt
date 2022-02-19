package cz.stepes.githubviewer.ui.test

import androidx.compose.runtime.mutableStateOf
import cz.stepes.githubviewer.data.remote.GitHubService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TestViewModel(
    private val gitHubService: GitHubService
) : ViewModel() {

    val user = mutableStateOf<UserResponse?>(null)
    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)

    init {
        loadUser("stepesdrabes")
    }

    fun loadUser(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.value = true
            errorMessage.value = null

            val result = gitHubService.getUser(username = username)

            isLoading.value = false

            if (result.isSuccess) {
                user.value = result.getOrNull()

                return@launch
            }
        }
    }
}