package cz.stepes.githubviewer.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class CommitResponse(
    val sha: String
)