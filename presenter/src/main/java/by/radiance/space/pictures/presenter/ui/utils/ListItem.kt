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
    applicationSettings: ApplicationSettings,
    onClick: (() -> Unit)? = null,
    body: @Composable () -> Unit,
) {
    ListItem(CornerSize(applicationSettings.cornersSize.size), onClick, body)
}

@Composable
fun ListItem(
    cornerSize: CornerSize = CornerSize(5.dp),
    onClick: (() -> Unit)? = null,
    body: @Composable () -> Unit,
) {
    Card(
        modifier = Modifier.padding(5.dp),
        shape = MaterialTheme.shapes.medium.copy(all = cornerSize),
    ) {
        Box(modifier = Modifier.clickable { onClick?.invoke() }) {
            body.invoke()
        }
    }
}
