package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.LocalRepository

class SavePictureUseCase(
    private val localRepository: LocalRepository,
) {
    suspend fun save(picture: Picture): Picture {
        return localRepository.save(picture)
    }
}