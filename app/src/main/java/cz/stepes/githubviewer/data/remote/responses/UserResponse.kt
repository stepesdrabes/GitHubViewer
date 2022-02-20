package cz.stepes.githubviewer.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val login: String,
    val id: Int,
    @SerialName("avatar_url")
    val avatarUrl: String,
    val url: String,
    val name: String?,
    val email: String?,
    val bio: String?,
    @SerialName("public_repos")
    val publicRepos: Int,
    @SerialName("followers")
    val followers: Int
)
