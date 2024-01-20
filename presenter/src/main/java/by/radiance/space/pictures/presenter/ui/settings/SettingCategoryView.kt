package by.radiance.space.pictures.presenter.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.ui.settings.setting.PickerSetting
import by.radiance.space.pictures.presenter.viewModel.settings.setting.Setting
import by.radiance.space.pictures.presenter.viewModel.settings.setting.SettingCategory

@Composable
fun SettingCategoryView(
    settingCategory: SettingCategory,
    onSettingChanged: (Setting, Setting.SettingChange) -> Unit,
) {
    Column {
        settingCategory.settings.forEach { setting ->
            when (setting) {
                is Setting.Picker -> {
                    PickerSetting(
                        setting = setting,
                        onOptionSelected = { index ->
                            onSettingChanged(setting, setting.change(index))
                        }
                    )
                }
                is Setting.Switch -> {

                }
            }
        }
    }
}

@Composable
fun SettingCategory.contentDescription() : String {
    return stringResource(
        id = R.string.setting_description,
        stringResource(title),
        stringResource(description ?: R.string.no_description)
    )
}

@Composable
fun Setting.contentDescription() : String {
    return stringResource(
        id = R.string.setting_description,
        stringResource(title),
        stringResource(description ?: R.string.no_description)
    )
}
