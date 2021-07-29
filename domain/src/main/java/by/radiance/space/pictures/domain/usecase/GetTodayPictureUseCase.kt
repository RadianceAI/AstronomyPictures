package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.RemoteRepository
import java.util.*

class GetTodayPictureUseCase(
    private val remoteRepository: RemoteRepository,
) {
    suspend fun get(): Picture {
        return remoteRepository.getPicture(Date())
    }
}