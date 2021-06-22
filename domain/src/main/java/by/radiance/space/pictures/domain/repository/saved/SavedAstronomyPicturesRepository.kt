package by.radiance.space.pictures.domain.repository.saved

import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.entity.PictureId

interface SavedAstronomyPicturesRepository {
    suspend fun getSavedPictures(): List<AstronomyPicture>
    suspend fun getSavedPictureById(id: PictureId): AstronomyPicture?
}