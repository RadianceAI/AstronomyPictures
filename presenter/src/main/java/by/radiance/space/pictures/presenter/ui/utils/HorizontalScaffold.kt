package by.radiance.space.pictures.presenter.ui.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
        modifier = modifier.fillMaxSize(),
    ) {
        startBar()
        Box(modifier.weight(1f)) {
            content()
        }
        endBar()
    }
}