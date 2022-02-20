package cz.stepes.githubviewer.ui.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@Composable
fun CircularImage(
    size: Dp,
    url: String,
    contentDescription: String? = null
) {
    Image(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(MaterialTheme.colors.surface),
        painter = rememberImagePainter(
            data = url,
            builder = {
                crossfade(true)
                crossfade(500)
                transformations(CircleCropTransformation())
            }
        ),
        contentDescription = contentDescription
    )
}