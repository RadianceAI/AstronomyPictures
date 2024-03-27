package by.radiance.space.pictures.domain.repository

import by.radiance.space.pictures.domain.entity.settings.ApplicationSettings
import by.radiance.space.pictures.domain.entity.settings.ApplicationTheme
import by.radiance.space.pictures.domain.entity.settings.CornersSize
import by.radiance.space.pictures.domain.entity.settings.ListArrangement
import by.radiance.space.pictures.domain.entity.settings.SafeArea
import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    val settings: Flow<ApplicationSettings>

    val theme: Flow<ApplicationTheme>
    val cornerSize: Flow<CornersSize>
    val safeArea: Flow<SafeArea>
    val listArrangement: Flow<ListArrangement>

    suspend fun editTheme(theme: ApplicationTheme)
    suspend fun editCornerSize(cornersSize: CornersSize)
    suspend fun editSafeArea(safeArea: SafeArea)
    suspend fun editListArrangement(listArrangement: ListArrangement)
}