package by.radiance.space.pictures.local.repository

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.local.entity.PictureDAO
import by.radiance.space.pictures.local.mapper.PictureMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class RoomAstronomyPicturesRepository(
    private val pictureDAO: PictureDAO
) {
    fun getAll(): Flow<List<Picture>> {
        return pictureDAO.getAll().map { pictures -> PictureMapper().map(pictures) }
    }

    fun find(startDate: Date, endDate: Date): Flow<List<Picture>> {
        return pictureDAO.findPictures(java.sql.Date(startDate.time), java.sql.Date(endDate.time))
            .map { pictures -> PictureMapper().map(pictures) }
    }

    fun getPicture(data: Date): Flow<Picture?> {
        return pictureDAO.getPicture(java.sql.Date(data.time))
            .map { picture ->
                picture?.let { PictureMapper().map(it) }
            }
    }

    suspend fun save(picture: Picture): Picture {
        val astronomyPicture = PictureMapper().map(picture).copy(
            saveDate = java.sql.Date(Date().time)
        )
        pictureDAO.insert(astronomyPicture)

        return PictureMapper().map(astronomyPicture)
    }

    suspend fun delete(picture: Picture) {
        pictureDAO.delete(java.sql.Date(picture.id.date.time))
    }
}