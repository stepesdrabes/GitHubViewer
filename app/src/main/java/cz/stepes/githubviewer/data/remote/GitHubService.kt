package cz.stepes.githubviewer.data.remote

import cz.stepes.githubviewer.data.remote.responses.CommitResponse
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import cz.stepes.githubviewer.util.Resource

interface GitHubService {

    suspend fun getUser(username: String): Resource<UserResponse>

    suspend fun getRepositories(username: String): Resource<List<RepositoryResponse>>

    suspend fun getCommits(username: String, repoName: String): Resource<List<CommitResponse>>
}