package by.radiance.space.preferencies

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import by.radiance.space.pictures.domain.entity.settings.ApplicationTheme
import by.radiance.space.pictures.domain.entity.settings.CornersSize
import by.radiance.space.pictures.domain.repository.SettingRepository
import by.radiance.space.preferencies.map.ApplicationThemeMapper
import by.radiance.space.preferencies.map.CornerSizeMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreSettingsRepository(
    context: Context,
) : SettingRepository {

    private val dataStore: DataStore<Preferences> = context.dataStore

    private val applicationThemeMapper = ApplicationThemeMapper(
        themeKey = applicationThemeKey,
        defaultTheme = ApplicationTheme.Dark,
    )

    private val cornerSizeMapper = CornerSizeMapper(
        themeKey = cornerSizeKey,
        defaultCornersSize = CornersSize(5)
    )

    override val theme: Flow<ApplicationTheme> = dataStore.data.map(applicationThemeMapper::map)
    override val cornerSize: Flow<CornersSize> = dataStore.data.map(cornerSizeMapper::map)

    override suspend fun editTheme(theme: ApplicationTheme) {
        dataStore.edit { preferences ->
            preferences[applicationThemeKey] = applicationThemeMapper.map(theme)
        }
    }

    override suspend fun editCornerSize(cornersSize: CornersSize) {
        dataStore.edit { preferences ->
            preferences[cornerSizeKey] = cornerSizeMapper.map(cornersSize)
        }
    }

    companion object {
        private val applicationThemeKey = intPreferencesKey("ApplicationTheme")
        private val cornerSizeKey = intPreferencesKey("CornersSize")
    }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
