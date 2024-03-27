package by.radiance.space.pictures.presenter.ui.abount

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.utils.SafeArea

@Composable
fun AboutView(
    modifier: Modifier = Modifier
) {
    SafeArea {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = stringResource(id = R.string.application_info),
                style = MaterialTheme.typography.body2,
            )
            Text(
                text = stringResource(id = R.string.apod),
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = stringResource(id = R.string.apod_info_1),
                style = MaterialTheme.typography.body2,
            )
            Text(
                text = stringResource(id = R.string.apod_info_2),
                style = MaterialTheme.typography.body2,
            )
            Text(
                text = stringResource(id = R.string.apod_info_3),
                style = MaterialTheme.typography.body2,
            )
            Text(
                text = stringResource(id = R.string.about_image_permissions),
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = stringResource(id = R.string.permissions_details),
                style = MaterialTheme.typography.body2,
            )
        }
    }
}

@Preview
@Composable
fun AboutPreview() {
    AstronomyPicturesTheme(darkTheme = true) {
        AboutView()
    }
}