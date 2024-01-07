package by.radiance.space.pictures.presenter.ui.progress

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme

@Composable
fun Progress(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Preview(
    widthDp = 400,
    heightDp = 400,
)
@Composable
fun ProgressPreview() {
    AstronomyPicturesTheme {
        Progress()
    }
}