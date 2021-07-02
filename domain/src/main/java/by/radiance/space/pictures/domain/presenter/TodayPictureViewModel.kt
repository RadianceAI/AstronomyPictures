package by.radiance.space.pictures.domain.presenter

import androidx.lifecycle.LiveData
import by.radiance.space.pictures.domain.entity.AstronomyPicture

interface TodayPictureViewModel {
    val todayPicture: LiveData<AstronomyPicture>

    fun init()
}