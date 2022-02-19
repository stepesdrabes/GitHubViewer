package cz.stepes.githubviewer.data.remote

import cz.stepes.githubviewer.data.remote.responses.RepositoryResponse
import cz.stepes.githubviewer.data.remote.responses.UserResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface GitHubService {

    suspend fun getUser(username: String): Result<UserResponse?>

    suspend fun getRepositories(username: String): Result<List<RepositoryResponse>?>

    companion object {
        fun create(): GitHubService {
            return GitHubServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }

                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}