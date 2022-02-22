package cz.stepes.githubviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost
import cz.stepes.githubviewer.ui.NavGraphs
import cz.stepes.githubviewer.ui.shared.theme.GitHubViewerTheme
import cz.stepes.githubviewer.util.LanguageColors
import cz.stepes.githubviewer.util.TimeUtil.toAgo

@ExperimentalFoundationApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GitHubViewerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LaunchedEffect(
                        key1 = "Language colors",
                        block = {
                            LanguageColors.initialize(resources = resources)
                        }
                    )

                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}