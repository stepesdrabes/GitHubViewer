package cz.stepes.githubviewer.data.remote.responses

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryResponse(
    val id: Int,
    val fullName: String,
    val owner: UserResponse,
    val htmlUrl: String,
    val description: String?,
    val language: String,
    val createdAt: LocalDateTime,
    val stargazersCount: Int,
    val watchersCount: Int,
    val forksCount: Int
)