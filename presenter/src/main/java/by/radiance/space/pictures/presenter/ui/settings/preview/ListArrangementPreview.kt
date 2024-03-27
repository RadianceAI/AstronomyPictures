package by.radiance.space.pictures.presenter.ui.settings.preview

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import by.radiance.space.pictures.domain.entity.settings.ApplicationTheme
import by.radiance.space.pictures.domain.entity.settings.ListArrangement
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme

@Composable
fun ListArrangementPreview(
    modifier: Modifier = Modifier,
    value: Int,
) {
    Row (
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(value.dp),
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .height(50.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colors.primary,
                    shape = MaterialTheme.shapes.medium,
                ),
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "$value",
            )
        }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .height(50.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colors.primary,
                    shape = MaterialTheme.shapes.medium,
                ),
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "$value",
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AstronomyPicturesTheme {
        ListArrangementPreview(value = 5)
    }
}

