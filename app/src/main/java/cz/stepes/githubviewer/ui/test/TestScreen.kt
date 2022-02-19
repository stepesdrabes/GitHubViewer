package cz.stepes.githubviewer.ui.test

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.ui.theme.spacing
import org.koin.androidx.compose.viewModel

@Composable
fun TestScreen() {
    val viewModel: TestViewModel by viewModel()

    Scaffold(
        topBar = {
            TopAppBar {
                /*Icon(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = null
                )*/

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.large))

                Text(text = stringResource(id = R.string.back))
            }
        },
        content = {
            LazyColumn(
                content = {
                    item {

                    }
                }
            )
        }
    )
}