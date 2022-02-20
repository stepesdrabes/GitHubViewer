package cz.stepes.githubviewer.data.remote

import cz.stepes.githubviewer.data.remote.responses.CommitResponse
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import cz.stepes.githubviewer.util.Resource
import cz.stepes.githubviewer.util.ResourceErrorState
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class GitHubServiceImpl(
    private val client: HttpClient
) : GitHubService {

    companion object {
        fun create(client: HttpClient): GitHubService {
            return GitHubServiceImpl(client = client)
        }
    }

    override suspend fun getUser(username: String): Resource<UserResponse> {
        return try {
            Resource.Success(
                client.get {
                    url("${HttpRoutes.USER_URL}/$username")
                }
            )
        } catch (exception: ClientRequestException) {
            return Resource.Error(errorState = ResourceErrorState.NotFound)
        } catch (exception: Exception) {
            return Resource.Error(errorState = ResourceErrorState.NetworkError)
        }
    }

    override suspend fun getRepositoriesList(username: String): Resource<List<RepositoryResponse>> {
        return try {
            Resource.Success(
                client.get {
                    url("${HttpRoutes.USER_URL}/$username/${HttpRoutes.REPOS}")
                }
            )
        } catch (exception: ClientRequestException) {
            return Resource.Error(errorState = ResourceErrorState.NotFound)
        } catch (exception: Exception) {
            return Resource.Error(errorState = ResourceErrorState.NetworkError)
        }
    }

    override suspend fun getCommits(
        username: String,
        repositoryName: String
    ): Resource<List<CommitResponse>> {
        return try {
            Resource.Success(
                client.get {
                    url("${HttpRoutes.REPOS_URL}/$username/$repositoryName/${HttpRoutes.COMMITS}")
                }
            )
        } catch (exception: ClientRequestException) {
            return Resource.Error(errorState = ResourceErrorState.NotFound)
        } catch (exception: Exception) {
            return Resource.Error(errorState = ResourceErrorState.NetworkError)
        }
    }

    override suspend fun getRepository(
        username: String,
        repositoryName: String
    ): Resource<RepositoryResponse> {
        return try {
            Resource.Success(
                client.get {
                    url("${HttpRoutes.REPOS_URL}/$username/$repositoryName")
                }
            )
        } catch (exception: ClientRequestException) {
            return Resource.Error(errorState = ResourceErrorState.NotFound)
        } catch (exception: Exception) {
            return Resource.Error(errorState = ResourceErrorState.NetworkError)
        }
    }

    override suspend fun getLanguages(
        username: String,
        repositoryName: String
    ): Resource<Map<String, Int>> {
        return try {
            Resource.Success(
                client.get {
                    url("${HttpRoutes.REPOS_URL}/$username/$repositoryName/languages")
                }
            )
        } catch (exception: ClientRequestException) {
            return Resource.Error(errorState = ResourceErrorState.NotFound)
        } catch (exception: Exception) {
            return Resource.Error(errorState = ResourceErrorState.NetworkError)
        }
    }
}