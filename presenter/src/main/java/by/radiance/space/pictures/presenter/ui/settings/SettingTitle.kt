package by.radiance.space.pictures.presenter.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme

@Composable
fun Title(
    icon: ImageVector,
    title: String,
    description: String?,
    contentDescription: String,
) {
    Row(
        modifier = Modifier
            .padding(8.dp),
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(8.dp),
            imageVector = icon,
            contentDescription = contentDescription,
        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f),
        ) {
            Text(
                modifier = Modifier
                    .wrapContentHeight(),
                text = title,
                style = MaterialTheme.typography.h1
            )
            if (description != null) {
                Text(
                    modifier = Modifier,
                    text = description,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}

@Preview
@Composable
fun TitlePreview() {
    AstronomyPicturesTheme {
        Title(
            icon = Icons.Filled.Star,
            title = "Title",
            description = "Description",
            contentDescription = ""
        )
    }
}

@Preview
@Composable
fun TitlePreviewWithoutDescription() {
    AstronomyPicturesTheme {
        Title(
            icon = Icons.Filled.Star,
            title = "Title",
            description = null,
            contentDescription = ""
        )
    }
}
