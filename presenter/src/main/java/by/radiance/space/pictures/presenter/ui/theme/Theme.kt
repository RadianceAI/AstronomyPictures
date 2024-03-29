package by.radiance.space.pictures.presenter.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.domain.entity.settings.CornersSize
import by.radiance.space.pictures.domain.entity.settings.ListArrangement

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun AstronomyPicturesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    cornersSize: Int = 0,
    safeArea: Dp = 0.dp,
    listArrangement: Dp = 0.dp,
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(
        LocalListArrangement provides listArrangement,
        LocalSafeArea provides safeArea,
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes(cornersSize),
            content = content
        )
    }
}


val MaterialTheme.listArrangementSpace: Dp
    @Composable
    @ReadOnlyComposable
    get() = LocalListArrangement.current

val MaterialTheme.arrangement: Arrangement.HorizontalOrVertical
    @Composable
    @ReadOnlyComposable
    get() = Arrangement.spacedBy(MaterialTheme.listArrangementSpace)

val MaterialTheme.safeArea: Dp
        @Composable
        @ReadOnlyComposable
        get() = LocalSafeArea.current

internal val LocalListArrangement = staticCompositionLocalOf { 15.dp }
internal val LocalSafeArea = staticCompositionLocalOf { 15.dp }