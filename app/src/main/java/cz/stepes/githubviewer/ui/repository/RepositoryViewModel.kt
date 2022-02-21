package cz.stepes.githubviewer.ui.repository

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.stepes.githubviewer.data.remote.GitHubService
import cz.stepes.githubviewer.data.remote.responses.BranchResponse
import cz.stepes.githubviewer.data.remote.responses.CommitResponse
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.util.Resource
import kotlinx.coroutines.launch

class RepositoryViewModel(
    private val gitHubService: GitHubService
) : ViewModel() {

    private var currentUser: String? = null
    private var currentRepository: String? = null

    val repositoryState = mutableStateOf<Resource<RepositoryResponse>>(Resource.Loading())
    val languagesState = mutableStateOf<Resource<Map<String, Int>>>(Resource.Loading())
    val branchesState = mutableStateOf<Resource<List<BranchResponse>>>(Resource.Loading())
    val commitsState = mutableStateOf<Resource<List<CommitResponse>>>(Resource.Loading())

    suspend fun getRepositoryInfo(username: String, repositoryName: String) {
        if (currentUser != username || currentRepository != repositoryName) {

            currentUser = username;
            currentRepository = repositoryName;

            viewModelScope.launch {
                repositoryState.value = gitHubService.getRepository(username, repositoryName)
                languagesState.value = gitHubService.getLanguages(username, repositoryName)
                branchesState.value = gitHubService.getBranches(username, repositoryName)
                commitsState.value = gitHubService.getCommits(username, repositoryName)
            }
        }
    }
}