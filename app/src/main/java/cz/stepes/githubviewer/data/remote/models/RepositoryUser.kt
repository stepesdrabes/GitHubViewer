package cz.stepes.githubviewer.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryUser(
    val login: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
)