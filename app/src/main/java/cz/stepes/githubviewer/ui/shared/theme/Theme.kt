package cz.stepes.githubviewer.ui.shared.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

val Colors.unimportantText: Color
    @Composable
    @ReadOnlyComposable
    get() = if (isLight) LightSmallTextOnBg else DarkSmallTextOnBg

private val DarkColorPalette = darkColors(
    primary = HighlightBlue,
    secondary = HighlightGreen,
    background = OffBlack,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    surface = DarkBoxBg,
    onSurface = DarkTextOnBox,
)

private val LightColorPalette = lightColors(
    primary = HighlightBlue,
    secondary = HighlightGreen,
    background = White,
    onPrimary = White,
    onSecondary = White,
    onBackground = OffBlack,
    surface = LightBoxBg,
    onSurface = LightTextOnBox,
)

@Composable
fun GitHubViewerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalTextSize provides TextSize(),
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}