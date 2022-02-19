package cz.stepes.githubviewer.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.stepes.githubviewer.data.remote.GitHubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val gitHubService: GitHubService
) : ViewModel() {

    fun searchUser(username: String) {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
}