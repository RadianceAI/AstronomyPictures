package by.radiance.space.pictures.presenter.ui.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.utils.stringResource
import by.radiance.space.pictures.presenter.viewModel.settings.setting.Setting
import by.radiance.space.pictures.presenter.viewModel.settings.setting.SettingCategory

@Composable
fun SettingsView(
    options: List<SettingCategory>,
    onSettingChanged: (Setting, Setting.SettingChange) -> Unit,
) {
    var selectedSettings by remember { mutableStateOf<SettingCategory?>(null) }

    AnimatedVisibility(visible = selectedSettings == null) {
        Column {
            options.forEach { option ->
                SettingRow(
                    option = option,
                    onClicked = {
                        selectedSettings = it
                    }
                )
            }
        }
    }

    AnimatedVisibility(visible = selectedSettings != null) {
        selectedSettings?.let {
            Column {
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            selectedSettings = null
                        },
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                )
                Box(
                    modifier = Modifier.weight(1f),
                ) {
                    SettingCategoryView(
                        settingCategory = it,
                        onSettingChanged = onSettingChanged,
                    )
                }
            }
        }
    }
}

@Composable
fun SettingRow(
    option: SettingCategory,
    onClicked: (SettingCategory) -> Unit,
) {
    Box(
        modifier = Modifier
            .clickable {
                onClicked(option)
            },
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
            option = SettingCategory(
                icon = Icons.Filled.Star,
                title = R.string.app_name,
                description = R.string.app_name,
                settings = listOf()
            ),
            onClicked = {
            },
        )
    }
}