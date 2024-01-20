package by.radiance.space.pictures.presenter.viewModel.settings

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.ui.settings.appearance.Theme
import by.radiance.space.pictures.presenter.viewModel.settings.setting.Setting
import by.radiance.space.pictures.presenter.viewModel.settings.setting.SettingCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {

    private val settingsEmitter = MutableStateFlow<List<SettingCategory>>(settings(0))
    val settings: Flow<List<SettingCategory>> = settingsEmitter

    private fun settings(
        selectedTheme: Int,
    ) = listOf(
        SettingCategory(
            icon = Icons.Filled.Palette,
            title = R.string.appearance,
            description = null,
            settings = listOf(
                Setting.Picker(
                    id = "Appearance.Theme",
                    icon = Icons.Filled.Palette,
                    title = R.string.theme,
                    description = null,
                    options = listOf(
                        Setting.Picker.Option(
                            title = null,
                            description = null,
                            view = @Composable { selected ->
                                Theme(
                                    isDark = true,
                                    isSelected = selected,
                                    title = R.string.dark_theme,
                                )
                            },
                        ),
                        Setting.Picker.Option(
                            title = null,
                            description = null,
                            view = @Composable { selected ->
                                Theme(
                                    isDark = false,
                                    isSelected = selected,
                                    title = R.string.light_theme,
                                )
                            },
                        ),
                        Setting.Picker.Option(
                            title = null,
                            description = null,
                            view = @Composable { selected ->
                                Theme(
                                    isDark = isSystemInDarkTheme(),
                                    isSelected = selected,
                                    title = R.string.system_theme,
                                )
                            },
                        ),
                    ),
                    selectedItemIndex = selectedTheme
                )
            ),
        )
    )

    fun changeSetting(setting: Setting, changes: Setting.SettingChange) {
        when (changes) {
            is Setting.Picker.Change -> {
                viewModelScope.launch {
                    settingsEmitter.emit(settings(changes.index))
                }
            }
        }
    }
}