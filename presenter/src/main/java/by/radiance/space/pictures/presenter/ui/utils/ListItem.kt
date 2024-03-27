package by.radiance.space.pictures.presenter.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.domain.entity.settings.ApplicationSettings
import by.radiance.space.pictures.domain.entity.settings.ApplicationTheme

@Composable
fun ListItem(
    onClick: (() -> Unit)? = null,
    body: @Composable () -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.medium,
    ) {
        Box(modifier = Modifier.clickable { onClick?.invoke() }) {
            body.invoke()
        }
    }
}
