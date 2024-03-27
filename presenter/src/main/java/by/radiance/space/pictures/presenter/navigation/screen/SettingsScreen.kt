package by.radiance.space.pictures.presenter.navigation.screen

import android.os.Bundle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.RoundedCorner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import by.radiance.space.pictures.domain.entity.settings.ApplicationTheme
import by.radiance.space.pictures.domain.entity.settings.CornersSize
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.navigation.Router
import by.radiance.space.pictures.presenter.navigation.screen.base.Screen
import by.radiance.space.pictures.presenter.ui.settings.SettingsView
import by.radiance.space.pictures.presenter.ui.settings.preview.CornersSizePreview
import by.radiance.space.pictures.presenter.ui.settings.preview.ThemePreview
import by.radiance.space.pictures.presenter.ui.settings.model.Setting
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.viewModel.SettingsViewModel
import by.radiance.space.pictures.presenter.ui.settings.model.SettingCategory
import org.koin.androidx.compose.getViewModel

class SettingsScreen : Screen {
    @Composable
    override fun View(router: Router, arguments: Bundle?, heightWindowSize: WindowSize) {
        val viewModel = getViewModel<SettingsViewModel>()

        val settings by viewModel.settings.collectAsState()

        val categories = remember {
            listOf(
                SettingCategory(
                    icon = Icons.Filled.Palette,
                    title = R.string.appearance,
                    description = null,
                    settings = listOf(
                        ThemePicker { theme -> settings.theme == theme },
                        CornerSizeSlider { settings.cornersSize },
                    ),
                )
            )
        }

        SettingsView(
            categories = categories,
            onSettingChanged = viewModel::changeSetting,
        )
    }

    private fun ThemePicker(isSelected: (ApplicationTheme) -> Boolean) =
        Setting.Picker(
            id = "Appearance.Theme",
            icon = Icons.Filled.Palette,
            title = R.string.theme,
            description = null,
            options = listOf(
                ApplicationTheme.Dark,
                ApplicationTheme.Light,
                ApplicationTheme.System
            ),
            isSelected = isSelected,
            preview = { theme ->
                ThemePreview(theme = theme)
            },
        )

    private fun CornerSizeSlider(cornersSize: () -> CornersSize) =
        Setting.Slider(
            id = "Appearance.CornersSize",
            icon = Icons.Filled.RoundedCorner,
            title = R.string.corners_size,
            description = null,
            steps = 25,
            valueRange = 0f..25f,
            value = {
                cornersSize().size
            },
            preview = { value ->
                CornersSizePreview(value = value)
            },
        )
}