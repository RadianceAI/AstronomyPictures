package by.radiance.space.pictures.domain.presenter

import androidx.lifecycle.LiveData
import by.radiance.space.pictures.domain.entity.AstronomyPicture

interface PictureListViewModel {
    val astronomyPictureList: LiveData<List<AstronomyPicture>>

    fun init()
}