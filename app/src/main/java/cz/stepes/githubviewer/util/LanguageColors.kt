package cz.stepes.githubviewer.util

import android.content.res.Resources
import androidx.compose.ui.graphics.Color
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.data.models.LanguageColor
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class LanguageColors {

    companion object {

        private lateinit var colors: Map<String, LanguageColor>

        private val formatter = Json {
            isLenient = true
            ignoreUnknownKeys = true
        }

        fun initialize(resources: Resources) {
            val text = resources.openRawResource(R.raw.colors).bufferedReader().use {
                it.readText()
            }

            colors = formatter.decodeFromString<Map<String, LanguageColor>>(string = text)
        }

        fun getLanguageColor(language: String): Color? {
            colors[language]?.let {
                if (it.color == null) {
                    return null
                }

                return Color(android.graphics.Color.parseColor(it.color))
            }

            return null
        }
    }
}