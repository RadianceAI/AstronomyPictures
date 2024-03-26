package by.radiance.space.pictures.presenter.ui.settings.appearance

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.domain.entity.settings.ApplicationSettings
import by.radiance.space.pictures.domain.entity.settings.ApplicationTheme
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme

@Composable
fun Theme(
    isDark: Boolean,
    isSelected: Boolean,
    title: Int,
) {
    AstronomyPicturesTheme(darkTheme = isDark) {
        Card(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = if (isSelected) MaterialTheme.colors.primary else Color.LightGray,
                    shape = MaterialTheme.shapes.medium.copy(all = CornerSize(8.dp))
                ),
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                text = stringResource(title),
            )
        }
    }
}

@Preview
@Composable
fun DarkTheme() {
    AstronomyPicturesTheme {
        Theme(isDark = true, isSelected = false, title = R.string.theme)
    }
}

@Preview
@Composable
fun LightTheme() {
    AstronomyPicturesTheme {
        Theme(isDark = false, isSelected = true, title = R.string.theme)
    }
}
