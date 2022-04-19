package by.radiance.space.pictures.domain.repository

import android.graphics.drawable.Drawable

interface WallpaperRepository {
    fun setSystemWallpaper(wallpaper: Drawable)
    fun setLockScreenWallpaper(wallpaper: Drawable)
    fun setAllWallpaper(wallpaper: Drawable)
}