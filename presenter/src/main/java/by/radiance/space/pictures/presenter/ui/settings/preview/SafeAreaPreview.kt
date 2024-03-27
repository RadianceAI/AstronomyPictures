package by.radiance.space.pictures.presenter.ui.settings.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.utils.dashedBorder

@Composable
fun SafeAreaPreview(
    modifier: Modifier = Modifier,
    value: Int,
) {
    Box (
        modifier = modifier
            .height(50.dp)
            .dashedBorder(
                color = Color.Gray,
                shape = RoundedCornerShape(0.dp),
                strokeWidth = 2.dp
            )
            .padding(start = value.dp, end = value.dp, top = value.dp),
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colors.primary,
                    shape = MaterialTheme.shapes.medium,
                )
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = value.toString()
            )
        }
    }
}

@Preview(
    widthDp = 100,
    heightDp = 100
)
@Composable
private fun Preview() {
    AstronomyPicturesTheme {
        SafeAreaPreview(value = 15)
    }
}