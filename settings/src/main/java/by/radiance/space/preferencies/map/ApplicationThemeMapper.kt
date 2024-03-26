package by.radiance.space.preferencies.map

import androidx.datastore.preferences.core.Preferences
import by.radiance.space.pictures.domain.entity.settings.ApplicationTheme

class ApplicationThemeMapper(
    private val themeKey: Preferences.Key<Int>,
    private val defaultTheme: ApplicationTheme
) {
    fun map(theme: ApplicationTheme): Int {
        return when (theme) {
            ApplicationTheme.Dark -> 0
            ApplicationTheme.Light -> 1
            ApplicationTheme.System -> 2
        }
    }

    fun map(preferences: Preferences): ApplicationTheme {
        return when (preferences[themeKey]) {
            0 -> ApplicationTheme.Dark
            1 -> ApplicationTheme.Light
            2 -> ApplicationTheme.System
            else -> defaultTheme
        }
    }
}