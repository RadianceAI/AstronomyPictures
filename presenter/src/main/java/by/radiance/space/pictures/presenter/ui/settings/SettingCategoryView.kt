package by.radiance.space.pictures.presenter.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import by.radiance.space.pictures.domain.entity.settings.ApplicationSettings
import by.radiance.space.pictures.presenter.ui.settings.model.Setting
import by.radiance.space.pictures.presenter.ui.settings.model.SettingCategory
import by.radiance.space.pictures.presenter.ui.settings.setting.PickerSetting
import by.radiance.space.pictures.presenter.ui.settings.setting.SliderSettings
import by.radiance.space.pictures.presenter.ui.utils.ListItem

@Composable
fun SettingCategoryView(
    modifier: Modifier = Modifier,
    applicationSettings: ApplicationSettings,
    settingCategory: SettingCategory,
    onSettingChanged: (Setting, Setting.SettingChange) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        settingCategory.settings.forEach { setting ->
            when (setting) {
                is Setting.Picker<*> -> {
                    ListItem(applicationSettings) {
                        PickerSetting(
                            setting = setting as Setting.Picker<Any>,
                            onOptionSelected = { change ->
                                onSettingChanged(setting, change)
                            }
                        )
                    }
                }
                is Setting.Slider -> {
                    ListItem(applicationSettings) {
                        SliderSettings(
                            setting = setting,
                            steps = 25,
                            valueRange = 0f..25f,
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
