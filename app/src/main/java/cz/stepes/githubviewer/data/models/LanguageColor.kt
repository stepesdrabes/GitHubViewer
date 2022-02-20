package cz.stepes.githubviewer.data.models

import kotlinx.serialization.Serializable

@Serializable
data class LanguageColor(
    val color: String?,
    val url: String
)
