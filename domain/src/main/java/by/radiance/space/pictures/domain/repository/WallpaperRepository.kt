package by.radiance.space.pictures.domain.repository

import android.graphics.drawable.Drawable

interface WallpaperRepository {
    suspend fun setWallpaper(drawable: Drawable, flags: Int)
}