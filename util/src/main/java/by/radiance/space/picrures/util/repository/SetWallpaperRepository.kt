package by.radiance.space.picrures.util.repository

import android.app.WallpaperManager
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import by.radiance.space.pictures.domain.repository.WallpaperRepository

class SetWallpaperRepository(
    private val context: Context,
): WallpaperRepository {

    override suspend fun setWallpaper(drawable: Drawable, flags: Int) {
        val wallpaperManager = WallpaperManager.getInstance(context)

        val bitmap = (drawable as BitmapDrawable).bitmap!!
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            wallpaperManager.setBitmap(
                bitmap,
                null,
                true,
                WallpaperManager.FLAG_SYSTEM
            )
        } else {
            wallpaperManager.setBitmap(bitmap)
        }
    }
}