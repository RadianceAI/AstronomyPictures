package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.RemoteRepository

class GetRandomPictureUseCase(
    private val remoteRepository: RemoteRepository,
) {
    suspend fun get(): Picture {
        return remoteRepository.getRandomPicture(1).first()
    }
}