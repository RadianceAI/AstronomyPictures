package by.radiance.space.pictures.domain.usecase.saved

import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.entity.PictureId
import by.radiance.space.pictures.domain.repository.saved.SavedAstronomyPicturesRepository

class SavedAstronomyPicturesUseCase(
    private val savedAstronomyPicturesRepository: SavedAstronomyPicturesRepository
) {
    suspend fun get(): List<AstronomyPicture> {
        return savedAstronomyPicturesRepository.getSavedPictures()
    }

    suspend fun get(id: PictureId): AstronomyPicture? {
        return savedAstronomyPicturesRepository.getSavedPictureById(id)
    }
}