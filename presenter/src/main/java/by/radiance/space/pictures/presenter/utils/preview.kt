package by.radiance.space.pictures.presenter.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import by.radiance.space.pictures.domain.entity.settings.ApplicationSettings
import by.radiance.space.pictures.domain.entity.settings.ApplicationTheme
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.ui.settings.model.SettingCategory

val settingCategory = SettingCategory(
    icon = Icons.Filled.Star,
    title = R.string.app_name,
    description = R.string.app_name,
    settings = listOf()
)