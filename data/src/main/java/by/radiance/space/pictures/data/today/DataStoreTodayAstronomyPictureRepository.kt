package by.radiance.space.pictures.data.today

import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.repository.today.TodayAstronomyPictureRepository

class DataStoreTodayAstronomyPictureRepository: TodayAstronomyPictureRepository {
    private var todayPicture: AstronomyPicture? = null

    override suspend fun get(): AstronomyPicture? {
        return todayPicture
    }

    override suspend fun save(picture: AstronomyPicture): AstronomyPicture {
        todayPicture = picture
        return picture
    }
}