package cz.stepes.githubviewer.data.remote.responses

import cz.stepes.githubviewer.data.remote.models.UserMin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryResponse(
    val id: Int,
    val name: String,
    @SerialName("full_name")
    val fullName: String,
    val owner: UserMin,
    @SerialName("default_branch")
    val defaultBranch: String,
    val fork: Boolean,
    val description: String?,
    val homepage: String?,
    val language: String?,
    @SerialName("stargazers_count")
    val stargazersCount: Int,
    @SerialName("watchers_count")
    val watchersCount: Int,
    @SerialName("forks_count")
    val forksCount: Int,
)