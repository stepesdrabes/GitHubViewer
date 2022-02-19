package cz.stepes.githubviewer.ui.search.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import cz.stepes.githubviewer.R
import cz.stepes.githubviewer.ui.theme.spacing
import cz.stepes.githubviewer.ui.theme.textSize
import cz.stepes.githubviewer.util.KeyboardState
import cz.stepes.githubviewer.util.keyboardAsState

@Composable
fun SearchBar(
    placeholder: String,
    enabled: Boolean = true,
    onSearch: (String) -> Unit,
    onChange: ((String) -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
    ) {
        val textState = remember { mutableStateOf(TextFieldValue()) }

        val interactionSource = remember { MutableInteractionSource() }
        val isFocused by interactionSource.collectIsFocusedAsState()
        val keyboardState by keyboardAsState()

        BasicTextField(
            modifier = Modifier
                .weight(1.0f)
                .fillMaxHeight()
                .background(
                    MaterialTheme.colors.surface,
                    MaterialTheme.shapes.medium
                ),
            value = textState.value,
            onValueChange = {
                if (it.text.length < 39) {
                    textState.value = it
                }

                onChange?.invoke(it.text)
            },
            enabled = enabled,
            singleLine = true,
            cursorBrush = SolidColor(MaterialTheme.colors.primary),
            textStyle = LocalTextStyle.current.copy(
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.textSize.normal
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = MaterialTheme.spacing.medium),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (textState.value.text.isEmpty()) Text(
                        text = placeholder,
                        style = LocalTextStyle.current.copy(
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = MaterialTheme.textSize.normal
                        )
                    )

                    innerTextField()
                }
            },
            interactionSource = interactionSource
        )

        AnimatedVisibility(
            enter = expandHorizontally(),
            exit = shrinkHorizontally(),
            visible = isFocused && keyboardState == KeyboardState.Opened
        ) {
            Box(modifier = Modifier.width(MaterialTheme.spacing.medium))
        }

        AnimatedVisibility(
            modifier = Modifier.clip(MaterialTheme.shapes.medium),
            enter = expandHorizontally(),
            exit = shrinkHorizontally(),
            visible = isFocused && keyboardState == KeyboardState.Opened
        ) {
            Button(
                modifier = Modifier.size(48.dp),
                onClick = {
                    onSearch(textState.value.text)
                },
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_fi_rr_search),
                    contentDescription = stringResource(id = R.string.search)
                )
            }
        }
    }
}