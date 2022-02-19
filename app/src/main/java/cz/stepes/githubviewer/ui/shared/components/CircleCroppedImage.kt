package cz.stepes.githubviewer.ui.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@Composable
fun CircleCroppedImage(
    size: Dp,
    url: String,
    contentDescription: String? = null
) {
    Image(
        modifier = Modifier.size(size),
        painter = rememberImagePainter(
            data = url,
            builder = {
                transformations(CircleCropTransformation())
            }
        ),
        contentDescription = contentDescription
    )
}