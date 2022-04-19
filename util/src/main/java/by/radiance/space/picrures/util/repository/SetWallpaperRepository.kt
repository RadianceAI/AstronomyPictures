package by.radiance.space.picrures.util.repository

import android.app.WallpaperManager
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import by.radiance.space.pictures.domain.repository.WallpaperRepository

class SetWallpaperRepository(
    context: Context,
): WallpaperRepository {

    private val wallpaperManager = WallpaperManager.getInstance(context)

    override fun setSystemWallpaper(wallpaper: Drawable) {
        setWallpaper(wallpaper, WallpaperManager.FLAG_SYSTEM)
    }

    override fun setLockScreenWallpaper(wallpaper: Drawable) {
        setWallpaper(wallpaper, WallpaperManager.FLAG_LOCK)
    }


    override fun setAllWallpaper(wallpaper: Drawable) {
        val bitmap = (wallpaper as BitmapDrawable).bitmap
        wallpaperManager.setBitmap(bitmap)
    }

    private fun setWallpaper(wallpaper: Drawable, flag: Int) {
        val bitmap = (wallpaper as BitmapDrawable).bitmap

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            wallpaperManager.setBitmap(
                bitmap,
                null,
                true,
                flag
            )
        } else {
            wallpaperManager.setBitmap(bitmap)
        }
    }
}