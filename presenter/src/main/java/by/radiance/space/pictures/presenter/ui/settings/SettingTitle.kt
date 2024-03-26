package by.radiance.space.pictures.presenter.ui.settings

import android.content.res.Resources
import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.presenter.ui.settings.model.Setting
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.utils.ListItem
import by.radiance.space.pictures.presenter.utils.contentDescription
import by.radiance.space.pictures.presenter.utils.stringResource

@Composable
fun Title(modifier: Modifier = Modifier, setting: Setting) {
    Title(
        icon = setting.icon,
        title = stringResource(setting.title),
        description = setting.description.stringResource(),
        contentDescription = setting.contentDescription(),
    )
}

@Composable
fun Title(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    title: String,
    description: String? = null,
    contentDescription: String,
) {
    Row(
        modifier = modifier
            .padding(8.dp),
    ) {
        if (icon != null) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp),
                imageVector = icon,
                contentDescription = contentDescription,
            )
        }
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
        ListItem {
            Title(
                icon = Icons.Filled.Star,
                title = "Title",
                description = "Description",
                contentDescription = ""
            )
        }
    }
}

@Preview
@Composable
fun TitlePreviewWithoutDescription() {
    AstronomyPicturesTheme {
        ListItem {
            Title(
                icon = Icons.Filled.Star,
                title = "Title",
                description = null,
                contentDescription = ""
            )
        }
    }
}
