package by.radiance.space.pictures.domain.presenter

import androidx.core.widget.ListViewAutoScrollHelper
import androidx.lifecycle.LiveData
import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.entity.PictureId
import by.radiance.space.pictures.domain.entity.QrCode

interface PictureDetailsViewModel {
    val astronomyPicture: LiveData<AstronomyPicture>
    val qrCode: LiveData<QrCode>

    fun init(id: PictureId)
    fun save()
    fun setToBackground()
    fun setToLockScreen()
}