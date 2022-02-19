package cz.stepes.githubviewer.data.remote.responses

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val url: String,
    val name: String?,
    val email: String?,
    val bio: String?,
    val publicRepos: Int,
    val createdAt: LocalDateTime
)
