package cz.stepes.githubviewer.data.remote

import cz.stepes.githubviewer.data.remote.responses.CommitResponse
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import cz.stepes.githubviewer.util.Resource

interface GitHubService {

    suspend fun getUser(username: String): Resource<UserResponse>

    suspend fun getRepositoriesList(username: String): Resource<List<RepositoryResponse>>

    suspend fun getRepository(
        username: String,
        repositoryName: String
    ): Resource<RepositoryResponse>

    suspend fun getCommits(
        username: String,
        repositoryName: String
    ): Resource<List<CommitResponse>>

    suspend fun getLanguages(
        username: String,
        repositoryName: String
    ): Resource<Map<String, Int>>
}