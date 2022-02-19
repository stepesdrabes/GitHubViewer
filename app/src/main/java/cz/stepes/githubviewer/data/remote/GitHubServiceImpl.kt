package cz.stepes.githubviewer.data.remote

import cz.stepes.githubviewer.data.remote.responses.CommitResponse
import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.data.remote.responses.UserResponse
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

    override suspend fun getUser(username: String): Result<UserResponse?> {
        return try {
            Result.success(
                client.get {
                    url("${HttpRoutes.USER_URL}/$username")
                }
            )
        } catch (exception: ClientRequestException) {
            return Result.success(null)
        } catch (exception: Exception) {
            return Result.failure(exception = exception)
        }
    }

    override suspend fun getRepositories(username: String): Result<List<RepositoryResponse>?> {
        return try {
            Result.success(
                client.get {
                    url("${HttpRoutes.USER_URL}/$username/${HttpRoutes.REPOS}")
                }
            )
        } catch (exception: ClientRequestException) {
            return Result.success(null)
        } catch (exception: Exception) {
            return Result.failure(exception = exception)
        }
    }

    override suspend fun getCommits(
        username: String,
        repoName: String
    ): Result<List<CommitResponse>?> {
        TODO("Not yet implemented")
    }
}