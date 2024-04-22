package by.radiance.space.pictures.domain.repository

import by.radiance.space.pictures.domain.entity.Picture
import java.util.*

interface AstronomyPictureRepository {
    suspend fun getPictures(startDate: Date, endDate: Date): List<Picture>
}