package by.radiance.space.preferencies.map

import androidx.datastore.preferences.core.Preferences
import by.radiance.space.pictures.domain.entity.settings.SafeArea

class SafeAreaMapper(
    private val key: Preferences.Key<Int>,
    private val default: SafeArea,
) {
    fun map(value: SafeArea): Int {
        return value.size
    }

    fun map(preferences: Preferences): SafeArea {
        return if (preferences.contains(key)) {
            SafeArea(preferences[key] ?: default.size)
        } else {
            default
        }
    }
}