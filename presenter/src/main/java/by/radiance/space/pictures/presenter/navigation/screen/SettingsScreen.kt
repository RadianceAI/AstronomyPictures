package by.radiance.space.pictures.presenter.navigation.screen

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import by.radiance.space.pictures.presenter.navigation.Router
import by.radiance.space.pictures.presenter.navigation.screen.base.Screen
import by.radiance.space.pictures.presenter.ui.settings.SettingsView
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.viewModel.settings.SettingsViewModel
import org.koin.androidx.compose.getViewModel

class SettingsScreen : Screen {
    @Composable
    override fun View(router: Router, arguments: Bundle?, heightWindowSize: WindowSize) {
        val viewModel = getViewModel<SettingsViewModel>()

        val settings by remember { viewModel.settings }.collectAsState(emptyList())

        SettingsView(
            options = settings,
            onSettingChanged = viewModel::changeSetting,
        )
    }
}