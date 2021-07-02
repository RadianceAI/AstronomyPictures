package by.radiance.space.pictures.domain.presenter

import androidx.lifecycle.LiveData
import by.radiance.space.pictures.domain.entity.AstronomyPicture

interface RandomPictureViewModel {
    val randomPicture: LiveData<AstronomyPicture>

    fun init()
}