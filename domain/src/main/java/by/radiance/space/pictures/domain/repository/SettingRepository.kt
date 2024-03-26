package by.radiance.space.pictures.domain.repository

import by.radiance.space.pictures.domain.entity.settings.ApplicationTheme
import by.radiance.space.pictures.domain.entity.settings.CornersSize
import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    val theme: Flow<ApplicationTheme>
    val cornerSize: Flow<CornersSize>

    suspend fun editTheme(theme: ApplicationTheme)
    suspend fun editCornerSize(cornersSize: CornersSize)
}