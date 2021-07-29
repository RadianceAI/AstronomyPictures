package by.radiance.space.pictures.domain.repository

import by.radiance.space.pictures.domain.entity.Picture

interface WallpaperRepository {
    suspend fun setToLockScreen(picture: Picture)
    suspend fun setToBackground(picture: Picture)
}