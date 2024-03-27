package by.radiance.space.pictures.presenter.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.RoundedCorner
import androidx.compose.material.icons.filled.Star
import by.radiance.space.pictures.domain.entity.settings.ApplicationSettings
import by.radiance.space.pictures.domain.entity.settings.ApplicationTheme
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.ui.settings.model.Setting
import by.radiance.space.pictures.presenter.ui.settings.model.SettingCategory
import by.radiance.space.pictures.presenter.ui.settings.preview.CornersSizePreview
import by.radiance.space.pictures.presenter.ui.settings.preview.ThemePreview
import by.radiance.space.preferencies.di.settings

val settingCategory = SettingCategory(
    icon = Icons.Filled.Star,
    title = R.string.app_name,
    description = R.string.app_name,
    settings = listOf()
)

val picker = Setting.Picker<Any>(
    id = "",
    icon = Icons.Filled.Star,
    title = R.string.app_name,
    description = R.string.app_name,
    options = listOf(ApplicationTheme.Dark, ApplicationTheme.Light),
    isSelected = { true },
    preview = {
        ThemePreview(theme = it as ApplicationTheme)
    }
)

val slider =  Setting.Slider(
    id = "Appearance.CornersSize",
    icon = Icons.Filled.RoundedCorner,
    title = R.string.corners_size,
    description = null,
    steps = 25,
    valueRange = 0f..25f,
    value = {
        20
    },
    preview = { value ->
        CornersSizePreview(value = value)
    },
)

val category =  SettingCategory(
    icon = Icons.Filled.Palette,
    title = R.string.appearance,
    description = null,
    settings = listOf(
        picker,
        slider,
    ),
)
val category1 =  SettingCategory(
    icon = Icons.Filled.Palette,
    title = R.string.app_name,
    description = null,
    settings = listOf(
        picker,
        slider,
    ),
)

val category2 =  SettingCategory(
    icon = Icons.Filled.Palette,
    title = R.string.theme,
    description = null,
    settings = listOf(
        picker,
        slider,
    ),
)
