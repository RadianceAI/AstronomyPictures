package by.radiance.space.pictures.presenter.navigation.screen

import android.os.Bundle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.RoundedCorner
import androidx.compose.material.icons.filled.SafetyDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import by.radiance.space.pictures.domain.entity.settings.ApplicationTheme
import by.radiance.space.pictures.domain.entity.settings.CornersSize
import by.radiance.space.pictures.domain.entity.settings.ListArrangement
import by.radiance.space.pictures.domain.entity.settings.SafeArea
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
import by.radiance.space.pictures.presenter.ui.settings.preview.ListArrangementPreview
import by.radiance.space.pictures.presenter.ui.settings.preview.SafeAreaPreview
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
                        SafeAreaSlider { settings.safeArea },
                        ListArrangementSlider { settings.listArrangement },
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

    private fun SafeAreaSlider(safeArea: () -> SafeArea) =
        Setting.Slider(
            id = "Appearance.SafeArea",
            icon = Icons.Filled.SafetyDivider,
            title = R.string.safeArea,
            description = null,
            steps = 25,
            valueRange = 0f..25f,
            value = {
                safeArea().size
            },
            preview = { value ->
                SafeAreaPreview(value = value)
            },
        )
    private fun ListArrangementSlider(listArrangement: () -> ListArrangement) =
        Setting.Slider(
            id = "Appearance.ListArrangement",
            icon = Icons.Filled.FormatSize,
            title = R.string.list_arrangement,
            description = null,
            steps = 25,
            valueRange = 0f..25f,
            value = {
                listArrangement().size
            },
            preview = { value ->
                ListArrangementPreview(value = value)
            },
        )
}