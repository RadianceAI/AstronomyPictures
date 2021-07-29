package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.LocalRepository

class DeletePictureUseCase(
    private val localRepository: LocalRepository,
) {
    suspend fun delete(picture: Picture): Picture {
        return localRepository.delete(picture)
    }
}