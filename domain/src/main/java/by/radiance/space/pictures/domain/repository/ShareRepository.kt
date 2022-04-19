package by.radiance.space.pictures.domain.repository

import android.graphics.Bitmap
import android.graphics.drawable.Drawable

interface ShareRepository {
    fun share(drawable: Drawable)
}