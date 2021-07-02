package by.radiance.space.pictures.domain.usecase.random

import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.repository.remote.RemoteAstronomyPictureRepository
import by.radiance.space.pictures.domain.repository.token.TokenRepository

class RandomAstronomyPictureUseCase(
        private val remoteAstronomyPictureRepository: RemoteAstronomyPictureRepository,
        private val tokenRepository: TokenRepository,
) {

    suspend fun get(): AstronomyPicture? {
        return remoteAstronomyPictureRepository.getRandomAstronomyPictures(1, tokenRepository.get()).firstOrNull()
    }
}