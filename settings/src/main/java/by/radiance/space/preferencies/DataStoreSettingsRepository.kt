package by.radiance.space.preferencies

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import by.radiance.space.pictures.domain.entity.settings.ApplicationSettings
import by.radiance.space.pictures.domain.entity.settings.ApplicationTheme
import by.radiance.space.pictures.domain.entity.settings.CornersSize
import by.radiance.space.pictures.domain.entity.settings.ListArrangement
import by.radiance.space.pictures.domain.entity.settings.SafeArea
import by.radiance.space.pictures.domain.repository.SettingRepository
import by.radiance.space.preferencies.map.ApplicationThemeMapper
import by.radiance.space.preferencies.map.CornerSizeMapper
import by.radiance.space.preferencies.map.ListArrangementMapper
import by.radiance.space.preferencies.map.SafeAreaMapper
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

    private val safeAreaMapper = SafeAreaMapper(
        key = safeAreaKey,
        default = SafeArea(5)
    )

    private val listArrangementMapper = ListArrangementMapper(
        key = listArrangementKey,
        default = ListArrangement(5)
    )

    override val settings: Flow<ApplicationSettings> = dataStore.data.map { preferences ->
        ApplicationSettings(
            theme = applicationThemeMapper.map(preferences),
            cornersSize = cornerSizeMapper.map(preferences),
            safeArea = safeAreaMapper.map(preferences),
            listArrangement = listArrangementMapper.map(preferences),
        )
    }
    override val safeArea: Flow<SafeArea> = dataStore.data.map(safeAreaMapper::map)
    override val listArrangement: Flow<ListArrangement> = dataStore.data.map(listArrangementMapper::map)
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

    override suspend fun editSafeArea(safeArea: SafeArea) {
        dataStore.edit { preferences ->
            preferences[safeAreaKey] = safeAreaMapper.map(safeArea)
        }
    }

    override suspend fun editListArrangement(listArrangement: ListArrangement) {
        dataStore.edit { preferences ->
            preferences[listArrangementKey] = listArrangementMapper.map(listArrangement)
        }
    }

    companion object {
        private val applicationThemeKey = intPreferencesKey("ApplicationTheme")
        private val cornerSizeKey = intPreferencesKey("CornersSize")
        private val safeAreaKey = intPreferencesKey("SafeArea")
        private val listArrangementKey = intPreferencesKey("ListArrangement")
    }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
