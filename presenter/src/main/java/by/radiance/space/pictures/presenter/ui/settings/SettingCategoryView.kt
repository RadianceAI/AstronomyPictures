package by.radiance.space.pictures.presenter.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import by.radiance.space.pictures.presenter.ui.settings.model.Setting
import by.radiance.space.pictures.presenter.ui.settings.model.SettingCategory
import by.radiance.space.pictures.presenter.ui.settings.setting.PickerSetting
import by.radiance.space.pictures.presenter.ui.settings.setting.SliderSettings
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.theme.arrangement
import by.radiance.space.pictures.presenter.ui.utils.ListItem
import by.radiance.space.pictures.presenter.utils.category
import by.radiance.space.pictures.presenter.utils.settingCategory

@Composable
fun SettingCategoryView(
    modifier: Modifier = Modifier,
    settingCategory: SettingCategory,
    onSettingChanged: (Setting, Setting.SettingChange) -> Unit,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = MaterialTheme.arrangement,
    ) {
        settingCategory.settings.forEach { setting ->
            when (setting) {
                is Setting.Picker<*> -> {
                    ListItem {
                        PickerSetting(
                            setting = setting as Setting.Picker<Any>,
                            onOptionSelected = { change ->
                                onSettingChanged(setting, change)
                            }
                        )
                    }
                }
                is Setting.Slider -> {
                    ListItem {
                        SliderSettings(
                            setting = setting,
                            onOptionChanged = { change ->
                                onSettingChanged(setting, change)
                            }
                        )
                    }
                }
                is Setting.Switch -> {

                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AstronomyPicturesTheme {
        SettingCategoryView(
            settingCategory = category,
            onSettingChanged = { _, _ -> }
        )
    }
}