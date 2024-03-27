package by.radiance.space.pictures.presenter.ui.settings.preview

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme

@Composable
fun CornersSizePreview(
    modifier: Modifier = Modifier,
    value: Int,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.primary,
                shape = MaterialTheme.shapes.medium.copy(
                    all = CornerSize(value.dp)
                )
            ),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "$value",
        )
    }
}

@Preview(
    widthDp = 100,
    heightDp = 100,
)
@Composable
private fun CornersSize() {
    AstronomyPicturesTheme {
        CornersSizePreview(value = 20)
    }
}

@Preview(
    widthDp = 100,
    heightDp = 100,
)
@Composable
private fun CornersSizeZero() {
    AstronomyPicturesTheme {
        CornersSizePreview(value = 0)
    }
}
