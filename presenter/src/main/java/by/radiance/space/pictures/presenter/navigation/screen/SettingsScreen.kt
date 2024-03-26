package by.radiance.space.pictures.presenter.navigation.screen

import android.os.Bundle
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.RoundedCorner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.domain.entity.settings.ApplicationSettings
import by.radiance.space.pictures.domain.entity.settings.ApplicationTheme
import by.radiance.space.pictures.domain.entity.settings.CornersSize
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.navigation.Router
import by.radiance.space.pictures.presenter.navigation.screen.base.Screen
import by.radiance.space.pictures.presenter.ui.settings.SettingsView
import by.radiance.space.pictures.presenter.ui.settings.appearance.Theme
import by.radiance.space.pictures.presenter.ui.settings.model.Setting
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.viewModel.SettingsViewModel
import by.radiance.space.pictures.presenter.ui.settings.model.SettingCategory
import okhttp3.internal.applyConnectionSpec
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
            applicationSettings = settings,
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
            preview = { theme ->
                Theme(
                    isSelected = isSelected(theme),
                    isDark = when (theme) {
                        ApplicationTheme.Dark -> true
                        ApplicationTheme.Light -> false
                        ApplicationTheme.System -> isSystemInDarkTheme()
                    },
                    title = when (theme) {
                        ApplicationTheme.Dark -> R.string.dark_theme
                        ApplicationTheme.Light -> R.string.light_theme
                        ApplicationTheme.System -> R.string.system_theme
                    }
                )
            },
        )

    private fun CornerSizeSlider(cornersSize: () -> CornersSize) =
        Setting.Slider(
            id = "Appearance.CornersSize",
            icon = Icons.Filled.RoundedCorner,
            title = R.string.corners_size,
            description = null,
            value = {
                cornersSize().size
            },
            preview = { value ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colors.primary,
                            shape = MaterialTheme.shapes.medium.copy(
                                all = CornerSize(value.dp)
                            )
                        ),
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "$value",
                    )
                }
            }
        )
}