package cz.stepes.githubviewer.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.stepes.githubviewer.data.remote.GitHubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val gitHubService: GitHubService
) : ViewModel() {

    val isLoading = mutableStateOf(false)

    fun searchUser(username: String) {
        viewModelScope.launch(Dispatchers.Default) {
            isLoading.value = true
        }
    }
}