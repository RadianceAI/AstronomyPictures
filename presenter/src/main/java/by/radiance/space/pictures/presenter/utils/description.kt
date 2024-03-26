package by.radiance.space.pictures.presenter.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.ui.settings.model.Setting
import by.radiance.space.pictures.presenter.ui.settings.model.SettingCategory

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