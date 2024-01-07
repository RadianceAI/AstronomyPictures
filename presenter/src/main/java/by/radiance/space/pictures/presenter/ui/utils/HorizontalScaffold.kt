package by.radiance.space.pictures.presenter.ui.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HorizontalScaffold(
    modifier: Modifier = Modifier,
    startBar: @Composable () -> Unit = {},
    endBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier,
    ) {
        startBar()
        content()
        endBar()
    }
}