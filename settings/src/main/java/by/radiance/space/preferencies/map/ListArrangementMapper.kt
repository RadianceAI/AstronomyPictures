package by.radiance.space.preferencies.map

import androidx.datastore.preferences.core.Preferences
import by.radiance.space.pictures.domain.entity.settings.ListArrangement

class ListArrangementMapper(
    private val key: Preferences.Key<Int>,
    private val default: ListArrangement,
) {
    fun map(value: ListArrangement): Int {
        return value.size
    }

    fun map(preferences: Preferences): ListArrangement {
        return if (preferences.contains(key)) {
            ListArrangement(preferences[key] ?: default.size)
        } else {
            default
        }
    }
}