package by.radiance.space.pictures.presenter.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.radiance.space.pictures.domain.entity.settings.ApplicationSettings
import by.radiance.space.pictures.domain.entity.settings.ApplicationTheme
import by.radiance.space.pictures.domain.entity.settings.CornersSize
import by.radiance.space.pictures.domain.repository.SettingRepository
import by.radiance.space.pictures.presenter.ui.settings.model.Setting
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingRepository: SettingRepository,
) : ViewModel() {

    private val settingsEmitter = MutableStateFlow(createSettings())
    val settings: StateFlow<ApplicationSettings> = settingsEmitter
        .asStateFlow()

    init {
        viewModelScope.launch {
            settingRepository.cornerSize
                .zip(settingRepository.theme, ::Pair)
                .collect { (cornersSize, applicationTheme) ->
                    settingsEmitter.emit(createSettings(applicationTheme, cornersSize).also {
                        Log.d("TAG_TAG", "$it")
                    })
                }
        }
    }

    fun changeSetting(setting: Setting, changes: Setting.SettingChange) {
        when (changes) {
            is Setting.Picker.Change -> {
                viewModelScope.launch {
                    settingRepository.editTheme(ApplicationTheme.fromIndex(changes.index))
                }
            }
            is Setting.Slider.Change -> {
                viewModelScope.launch {
                    settingRepository.editCornerSize(CornersSize(changes.size))
                }
            }
        }
    }

    private fun createSettings(
        selectedTheme: ApplicationTheme = ApplicationTheme.Dark,
        cornersSize: CornersSize = CornersSize(0),
    ): ApplicationSettings {
        return ApplicationSettings(
            theme = selectedTheme,
            cornersSize = cornersSize,
        )
    }
}