package by.radiance.space.pictures.presenter.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.theme.CardGray

@Composable
fun ErrorCard(
    modifier: Modifier = Modifier,
    cornerSize: CornerSize = CornerSize(16.dp),
    error: String
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .clip(MaterialTheme.shapes.medium.copy(all = cornerSize))
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(CardGray.copy(0.2f))) {
            Text(
                text = error,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(
    widthDp = 400,
    heightDp = 400,
)
@Composable
fun Previre() {
    AstronomyPicturesTheme {
        ErrorCard(error = "Today picture is not ready yet")
    }
}