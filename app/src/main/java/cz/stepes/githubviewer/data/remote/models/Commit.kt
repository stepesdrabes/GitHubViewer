package cz.stepes.githubviewer.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Commit(
    @SerialName("author")
    val author: CommitAuthor,
    val message: String?
)