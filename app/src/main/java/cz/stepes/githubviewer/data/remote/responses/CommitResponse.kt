package cz.stepes.githubviewer.data.remote.responses

import cz.stepes.githubviewer.data.remote.models.Commit
import cz.stepes.githubviewer.data.remote.models.UserMin
import kotlinx.serialization.Serializable

@Serializable
data class CommitResponse(
    val sha: String,
    val commit: Commit,
    val author: UserMin?
)