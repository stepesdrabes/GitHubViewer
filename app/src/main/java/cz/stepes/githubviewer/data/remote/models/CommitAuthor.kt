package cz.stepes.githubviewer.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class CommitAuthor(
    val name: String,
    val email: String,
    val date: String
)