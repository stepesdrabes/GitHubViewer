package cz.stepes.githubviewer

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.widget.Toast
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

        /*val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Toast.makeText(applicationContext, "You are back online", Toast.LENGTH_SHORT).show()
            }
        })*/

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