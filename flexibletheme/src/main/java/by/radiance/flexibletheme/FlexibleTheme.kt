package by.radiance.flexibletheme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun FlexibleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    darkColorsPalette: Colors,
    lightColorsPalette: Colors,
    typography: androidx.compose.material.Typography,
    shapes: Shapes,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider {
        MaterialTheme(
            colors = if (darkTheme) darkColorsPalette else lightColorsPalette,
            typography = typography,
            shapes = shapes,
            content = content,
        )
    }
}
