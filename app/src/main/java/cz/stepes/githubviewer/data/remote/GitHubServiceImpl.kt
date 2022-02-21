package cz.stepes.githubviewer.data.remote

import cz.stepes.githubviewer.data.remote.responses.BranchResponse
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
        return sendGetRequest("${HttpRoutes.USER_URL}/$username")
    }

    override suspend fun getRepositoriesList(username: String): Resource<List<RepositoryResponse>> {
        return sendGetRequest("${HttpRoutes.USER_URL}/$username/${HttpRoutes.REPOS}")
    }

    override suspend fun getCommits(
        username: String,
        repositoryName: String
    ): Resource<List<CommitResponse>> {
        return sendGetRequest("${HttpRoutes.REPOS_URL}/$username/$repositoryName/${HttpRoutes.COMMITS}")
    }

    override suspend fun getRepository(
        username: String,
        repositoryName: String
    ): Resource<RepositoryResponse> {
        return sendGetRequest("${HttpRoutes.REPOS_URL}/$username/$repositoryName")
    }

    override suspend fun getLanguages(
        username: String,
        repositoryName: String
    ): Resource<Map<String, Int>> {
        return sendGetRequest("${HttpRoutes.REPOS_URL}/$username/$repositoryName/${HttpRoutes.LANGUAGES}")
    }

    override suspend fun getBranches(
        username: String,
        repositoryName: String
    ): Resource<List<BranchResponse>> {
        return sendGetRequest("${HttpRoutes.REPOS_URL}/$username/$repositoryName/${HttpRoutes.BRANCHES}")
    }

    private suspend inline fun <reified T> sendGetRequest(url: String): Resource<T> {
        return try {
            Resource.Success(
                client.get {
                    url(url)
                }
            )
        } catch (exception: ClientRequestException) {
            return when (exception.response.status.value) {
                403 -> Resource.Error(errorState = ResourceErrorState.RateLimited)
                404 -> Resource.Error(errorState = ResourceErrorState.NotFound)
                else -> Resource.Error(errorState = ResourceErrorState.NetworkError)
            }
        } catch (exception: Exception) {
            return Resource.Error(errorState = ResourceErrorState.NetworkError)
        }
    }
}