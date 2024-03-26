package by.radiance.space.pictures.presenter.ui.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.domain.entity.settings.ApplicationSettings
import by.radiance.space.pictures.domain.entity.settings.ApplicationTheme
import by.radiance.space.pictures.domain.entity.settings.CornersSize
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.ui.settings.model.Setting
import by.radiance.space.pictures.presenter.ui.settings.model.SettingCategory
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.utils.ListItem
import by.radiance.space.pictures.presenter.utils.contentDescription
import by.radiance.space.pictures.presenter.utils.settingCategory
import by.radiance.space.pictures.presenter.utils.stringResource
import okhttp3.internal.applyConnectionSpec

@Composable
fun SettingsView(
    applicationSettings: ApplicationSettings,
    categories: List<SettingCategory>,
    onSettingChanged: (Setting, Setting.SettingChange) -> Unit,
) {
    var selectedSettings by remember { mutableStateOf<SettingCategory?>(null) }

    AnimatedVisibility(visible = selectedSettings == null) {
        Column {
            categories.forEach { category ->
                SettingRow(
                    applicationSettings = applicationSettings,
                    option = category,
                    onClicked = {
                        selectedSettings = it
                    }
                )
            }
        }
    }

    AnimatedVisibility(visible = selectedSettings != null) {
        selectedSettings?.let { category ->
            Column {
                CategoryHeader(category = category) {
                    selectedSettings = null
                }
                SettingCategoryView(
                    modifier = Modifier.weight(1f),
                    applicationSettings = applicationSettings,
                    settingCategory = category,
                    onSettingChanged = onSettingChanged,
                )
            }
        }
    }
}

@Composable
private fun CategoryHeader(
    category: SettingCategory,
    onClicked: () -> Unit
) {
    Row(
        modifier = Modifier.padding(5.dp)
            .clickable {
                onClicked.invoke()
            },
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(5.dp),
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back),
        )
        Title(
            modifier = Modifier.align(Alignment.CenterVertically),
            title = stringResource(category.title),
            contentDescription = category.contentDescription(),
        )
    }
}

@Composable
private fun SettingRow(
    applicationSettings: ApplicationSettings,
    option: SettingCategory,
    onClicked: (SettingCategory) -> Unit,
) {
    ListItem(
        applicationSettings = applicationSettings,
        onClick = {
            onClicked(option)
        }
    ) {
        Title(
            icon = option.icon,
            title = stringResource(option.title),
            description = option.description.stringResource(),
            contentDescription = option.contentDescription()
        )
    }
}

@Preview
@Composable
fun RowPreview() {
    AstronomyPicturesTheme {
        SettingRow(
            applicationSettings = ApplicationSettings(ApplicationTheme.Dark, CornersSize(0)),
            option = settingCategory,
            onClicked = {
            },
        )
    }
}

@Preview
@Composable
fun HeaderPreview() {
    AstronomyPicturesTheme {
        CategoryHeader(
            category = settingCategory,
            onClicked = {}
        )
    }
}