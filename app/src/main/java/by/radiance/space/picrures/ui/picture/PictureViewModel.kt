package by.radiance.space.picrures.ui.picture

import androidx.lifecycle.LiveData
import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.entity.PictureId

interface PictureViewModel {
    val picture: LiveData<AstronomyPicture>

    fun setPicture(id: PictureId)
    fun onSaveClicked()
}