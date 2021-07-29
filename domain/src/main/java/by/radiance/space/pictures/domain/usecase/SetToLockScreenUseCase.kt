package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.WallpaperRepository

class SetToLockScreenUseCase(
    private val wallpaperRepository: WallpaperRepository,
) {
    suspend fun set(picture: Picture) {
        wallpaperRepository.setToLockScreen(picture)
    }
}