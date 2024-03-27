package by.radiance.space.pictures.presenter.ui.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
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
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.ui.settings.model.Setting
import by.radiance.space.pictures.presenter.ui.settings.model.SettingCategory
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.theme.arrangement
import by.radiance.space.pictures.presenter.ui.utils.ListItem
import by.radiance.space.pictures.presenter.ui.utils.SafeArea
import by.radiance.space.pictures.presenter.utils.category
import by.radiance.space.pictures.presenter.utils.category1
import by.radiance.space.pictures.presenter.utils.category2
import by.radiance.space.pictures.presenter.utils.contentDescription
import by.radiance.space.pictures.presenter.utils.settingCategory
import by.radiance.space.pictures.presenter.utils.stringResource

@Composable
fun SettingsView(
    categories: List<SettingCategory>,
    onSettingChanged: (Setting, Setting.SettingChange) -> Unit,
) {
    var selectedSettings by remember { mutableStateOf<SettingCategory?>(null) }

    SafeArea {
        AnimatedVisibility(visible = selectedSettings == null) {
            Column(
                verticalArrangement = MaterialTheme.arrangement
            ) {
                categories.forEach { category ->
                    SettingRow(
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
                        settingCategory = category,
                        onSettingChanged = onSettingChanged,
                    )
                }
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
        modifier = Modifier
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
    option: SettingCategory,
    onClicked: (SettingCategory) -> Unit,
) {
    ListItem(
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
private fun Preview() {
    AstronomyPicturesTheme {
        SettingsView(
            categories = listOf(category, category1, category2),
            onSettingChanged = { _, _ -> },
        )
    }
}

@Preview
@Composable
private fun RowPreview() {
    AstronomyPicturesTheme {
        SettingRow(
            option = settingCategory,
            onClicked = {},
        )
    }
}

@Preview
@Composable
private fun HeaderPreview() {
    AstronomyPicturesTheme {
        CategoryHeader(
            category = settingCategory,
            onClicked = {},
        )
    }
}