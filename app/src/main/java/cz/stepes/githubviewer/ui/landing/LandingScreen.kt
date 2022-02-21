package cz.stepes.githubviewer.ui.landing

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.ui.destinations.SearchScreenDestination
import cz.stepes.githubviewer.ui.shared.components.IconLabelButton
import cz.stepes.githubviewer.ui.shared.theme.spacing
import cz.stepes.githubviewer.ui.shared.theme.textSize

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Destination(start = true)
@Composable
fun LandingScreen(
    navigator: DestinationsNavigator
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.pagePadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.pagePadding)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(128.dp),
                painter = painterResource(id = R.drawable.ic_github_logo),
                tint = MaterialTheme.colors.onBackground,
                contentDescription = stringResource(id = R.string.app_name)
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colors.onBackground,
                fontSize = MaterialTheme.textSize.large,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

            Text(
                text = stringResource(id = R.string.landing_text),
                color = MaterialTheme.colors.onSurface,
                fontSize = MaterialTheme.textSize.normal,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        val clicked = remember { mutableStateOf(false) }

        IconLabelButton(
            modifier = Modifier.align(Alignment.BottomStart),
            label = stringResource(id = R.string.landing_button_text),
            iconId = R.drawable.ic_fi_rr_arrow_right,
            enabled = !clicked.value,
            onClick = {
                clicked.value = true
                navigator.navigate(SearchScreenDestination())
            }
        )
    }
}