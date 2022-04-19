package by.radiance.space.pictures.domain.usecase

import android.graphics.drawable.Drawable
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.WallpaperRepository

class SetWallpaperUseCase(
    private val wallpaperRepository: WallpaperRepository,
) {

    fun setSystemWallpaper(wallpaper: Drawable) {
        wallpaperRepository.setSystemWallpaper(wallpaper)
    }

    fun setLockScreenWallpaper(wallpaper: Drawable) {
        wallpaperRepository.setLockScreenWallpaper(wallpaper)
    }

    fun setAllWallpaper(wallpaper: Drawable) {
        wallpaperRepository.setAllWallpaper(wallpaper)
    }
}