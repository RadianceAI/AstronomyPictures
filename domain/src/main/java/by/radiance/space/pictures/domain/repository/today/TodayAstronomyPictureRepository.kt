package by.radiance.space.pictures.domain.repository.today

import by.radiance.space.pictures.domain.entity.AstronomyPicture

interface TodayAstronomyPictureRepository {
    suspend fun get(): AstronomyPicture?
    suspend fun saveTodayPicture(picture: AstronomyPicture): AstronomyPicture
    suspend fun saveRandomPicture(picture: AstronomyPicture): AstronomyPicture
}