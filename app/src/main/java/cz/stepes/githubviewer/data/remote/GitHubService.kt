package cz.stepes.githubviewer.data.remote

import cz.stepes.githubviewer.data.remote.responses.CommitResponse
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.data.remote.responses.UserResponse

interface GitHubService {

    suspend fun getUser(username: String): Result<UserResponse?>

    suspend fun getRepositories(username: String): Result<List<RepositoryResponse>?>

    suspend fun getCommits(username: String, repoName: String): Result<List<CommitResponse>?>
}