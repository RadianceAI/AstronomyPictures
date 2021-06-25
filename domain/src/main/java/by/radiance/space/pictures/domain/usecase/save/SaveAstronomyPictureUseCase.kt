package by.radiance.space.pictures.domain.usecase.save

import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.repository.saved.SavedAstronomyPicturesRepository

class SaveAstronomyPictureUseCase(
    private val savedAstronomyPicturesRepository: SavedAstronomyPicturesRepository
) {
    suspend fun save(astronomyPicture: AstronomyPicture): AstronomyPicture {
        return savedAstronomyPicturesRepository.save(astronomyPicture)
    }

    suspend fun delete(astronomyPicture: AstronomyPicture) {
        savedAstronomyPicturesRepository.delete(astronomyPicture)
    }
}