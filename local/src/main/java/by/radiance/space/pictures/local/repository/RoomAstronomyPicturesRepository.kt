package by.radiance.space.pictures.local.repository

import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.entity.PictureId
import by.radiance.space.pictures.domain.repository.saved.SavedAstronomyPicturesRepository

class RoomAstronomyPicturesRepository: SavedAstronomyPicturesRepository {
    override suspend fun getSavedPictures(): List<AstronomyPicture> {
        return emptyList()
    }

    override suspend fun getSavedPictureById(id: PictureId): AstronomyPicture? {
        return null
    }
}