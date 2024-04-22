package by.radiance.space.pictures.local.repository

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.AstronomyPictureRepository
import by.radiance.space.pictures.local.entity.PictureDAO
import by.radiance.space.pictures.local.mapper.PictureMapper
import by.radiance.space.source.LocalSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class RoomAstronomyPicturesRepository(
    private val pictureDAO: PictureDAO
) : LocalSource {
    private val pictureMapper = PictureMapper()

    override suspend fun getPictures(startDate: Date, endDate: Date): List<Picture> {
        return pictureDAO
            .getPictures(java.sql.Date(startDate.time), java.sql.Date(endDate.time))
            .map(pictureMapper::map)
    }

    override suspend fun save(pictures: List<Picture>) {
        pictures.forEach {
            save(it)
        }
    }

    suspend fun save(picture: Picture): Picture {
        val astronomyPicture = PictureMapper().map(picture).copy(
            saveDate = java.sql.Date(Date().time)
        )
        pictureDAO.insert(astronomyPicture)

        return PictureMapper().map(astronomyPicture)
    }
}