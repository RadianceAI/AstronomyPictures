package by.radiance.space.pictures.domain.usecase

import android.graphics.drawable.Drawable
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.WallpaperRepository

class SetWallpaperUseCase(
    private val wallpaperRepository: WallpaperRepository,
) {
    suspend fun set(wallpaper: Drawable, flag: Int) {
        wallpaperRepository.setWallpaper(wallpaper, flag)
    }
}