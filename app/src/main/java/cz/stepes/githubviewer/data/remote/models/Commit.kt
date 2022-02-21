package cz.stepes.githubviewer.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class Commit(
    val author: CommitAuthor,
    val message: String?
)