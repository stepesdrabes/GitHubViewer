package cz.stepes.githubviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import cz.stepes.githubviewer.ui.theme.GitHubViewerTheme
import cz.stepes.githubviewer.ui.theme.textSize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubViewerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Text(
                        text = "Jak se máš? :)",
                        fontSize = MaterialTheme.textSize.title,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}