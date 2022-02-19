package cz.stepes.githubviewer.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryResponse(
    val id: Int,
    @SerialName("full_name")
    val fullName: String,
    val owner: UserResponse,
    @SerialName("html_url")
    val htmlUrl: String,
    val description: String?,
    val language: String,
    @SerialName("stargazers_count")
    val stargazersCount: Int,
    @SerialName("watchers_count")
    val watchersCount: Int,
    @SerialName("forks_count")
    val forksCount: Int
)