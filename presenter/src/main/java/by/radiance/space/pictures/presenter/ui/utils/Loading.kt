package by.radiance.space.pictures.presenter.ui.utils

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.theme.ShimmerColorShades

@Composable
fun LoadingCard(
    modifier: Modifier = Modifier,
) {
    val transition = rememberInfiniteTransition(label = "")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        ),
        label = "",
    )

    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    Card(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(Modifier.fillMaxSize().background(brush)) {

        }
    }
}

@Composable
@Preview(
    widthDp = 400,
    heightDp = 400,
)
fun Preview() {
    AstronomyPicturesTheme {
        LoadingCard()
    }
}