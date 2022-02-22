package cz.stepes.githubviewer.util

import android.content.res.Resources
import androidx.compose.ui.graphics.Color
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.models.LanguageColor
import cz.stepes.githubviewer.ui.shared.theme.HighlightBlue
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object LanguageColors {

    private lateinit var colors: Map<String, LanguageColor>

    private val formatter = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    fun initialize(resources: Resources) {
        val text = resources.openRawResource(R.raw.colors).bufferedReader().use {
            it.readText()
        }

        colors = formatter.decodeFromString(string = text)
    }

    fun getLanguageColor(language: String): Color {
        colors[language]?.let {
            return try {
                Color(android.graphics.Color.parseColor(it.color))
            } catch (exception: Exception) {
                HighlightBlue
            }
        }

        return HighlightBlue
    }
}