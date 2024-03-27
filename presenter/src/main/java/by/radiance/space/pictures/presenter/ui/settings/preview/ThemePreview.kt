package by.radiance.space.pictures.presenter.ui.settings.preview

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.domain.entity.settings.ApplicationTheme
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme

@Composable
fun ThemePreview(
    modifier: Modifier = Modifier,
    theme: ApplicationTheme,
) {
    ThemePreview(
        modifier = modifier,
        isDark = when (theme) {
            ApplicationTheme.Dark -> true
            ApplicationTheme.Light -> false
            ApplicationTheme.System -> isSystemInDarkTheme()
        },
        title = when (theme) {
            ApplicationTheme.Dark -> R.string.dark_theme
            ApplicationTheme.Light -> R.string.light_theme
            ApplicationTheme.System -> R.string.system_theme
        }
    )
}

@Composable
fun ThemePreview(
    modifier: Modifier = Modifier,
    isDark: Boolean,
    title: Int,
) {
    AstronomyPicturesTheme(darkTheme = isDark) {
        Card(
            modifier = modifier,
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                text = stringResource(title),
            )
        }
    }
}

@Preview
@Composable
fun DarkTheme() {
    AstronomyPicturesTheme {
        ThemePreview(isDark = true, title = R.string.theme)
    }
}

@Preview
@Composable
fun LightTheme() {
    AstronomyPicturesTheme {
        ThemePreview(isDark = false, title = R.string.theme)
    }
}
