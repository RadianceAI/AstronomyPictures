package by.radiance.space.pictures.presenter.ui.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import by.radiance.space.pictures.presenter.ui.theme.safeArea

@Composable
fun SafeArea(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .padding(
                start = MaterialTheme.safeArea,
                end = MaterialTheme.safeArea,
                top = MaterialTheme.safeArea
            )
    ) {
        content()
    }
}