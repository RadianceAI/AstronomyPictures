package by.radiance.space.source

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.AstronomyPictureRepository

interface LocalSource : AstronomyPictureRepository {
    suspend fun save(pictures: List<Picture>)
}