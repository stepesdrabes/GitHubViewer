package cz.stepes.githubviewer.ui.aboutapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.ui.aboutapp.components.AppDescription
import cz.stepes.githubviewer.ui.aboutapp.components.AuthorDescription
import cz.stepes.githubviewer.ui.aboutapp.components.LibrariesList
import cz.stepes.githubviewer.ui.shared.components.IconLabelButton
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize

@Destination
@Composable
fun AboutAppScreen(
    navigator: DestinationsNavigator
) {
    val urlHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding / 2),
                elevation = 0.dp,
                content = {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .clip(MaterialTheme.shapes.medium)
                            .clickable {
                                navigator.navigateUp()
                            }
                            .padding(horizontal = MaterialTheme.spacing.pagePadding / 2),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_fi_br_arrow_left),
                            tint = MaterialTheme.colors.onBackground,
                            contentDescription = stringResource(id = R.string.back)
                        )

                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.large))

                        Text(
                            text = stringResource(id = R.string.back),
                            fontSize = MaterialTheme.textSize.medium,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colors.onBackground,
                        )
                    }
                }
            )
        },
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraLarge),
            contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding)
        ) {
            // Basic info about the app
            item { AppDescription() }

            // Libraries used
            item { LibrariesList() }

            // Open app github repo button
            item {
                val githubUri = stringResource(id = R.string.app_github_link)

                IconLabelButton(
                    label = stringResource(id = R.string.about_app_open_github_repo),
                    iconId = R.drawable.ic_fi_rr_arrow_right,
                    onClick = {
                        urlHandler.openUri(githubUri)
                    }
                )
            }

            // Author info
            item { AuthorDescription() }

            // Open app github repo button
            item {
                val authorUri = stringResource(id = R.string.author_github_link)

                IconLabelButton(
                    label = stringResource(id = R.string.about_app_open_author_github_profile),
                    iconId = R.drawable.ic_fi_rr_arrow_right,
                    onClick = {
                        urlHandler.openUri(authorUri)
                    }
                )
            }

            item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.large)) }
        }
    }
}