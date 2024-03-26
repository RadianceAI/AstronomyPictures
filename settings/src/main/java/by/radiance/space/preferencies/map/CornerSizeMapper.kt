package by.radiance.space.preferencies.map

import androidx.datastore.preferences.core.Preferences
import by.radiance.space.pictures.domain.entity.settings.CornersSize

class CornerSizeMapper(
    private val themeKey: Preferences.Key<Int>,
    private val defaultCornersSize: CornersSize,
) {
    fun map(cornersSize: CornersSize): Int {
        return cornersSize.size
    }

    fun map(preferences: Preferences): CornersSize {
        return if (preferences.contains(themeKey)) {
            CornersSize(preferences[themeKey] ?: defaultCornersSize.size)
        } else {
            defaultCornersSize
        }
    }
}