package by.radiance.space.pictures.domain.usecase.today

import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.repository.token.TokenRepository
import by.radiance.space.pictures.domain.repository.remote.RemoteAstronomyPictureRepository
import by.radiance.space.pictures.domain.repository.today.TodayAstronomyPictureRepository
import java.util.*

class TodayAstronomyPictureUseCase(
    private val tokenRepository: TokenRepository,
    private val remoteAstronomyPictureRepository: RemoteAstronomyPictureRepository,
    private val todayAstronomyPictureRepository: TodayAstronomyPictureRepository,
) {
    suspend fun get(): AstronomyPicture {
        val token = tokenRepository.get()

        val today = todayAstronomyPictureRepository.get()

        return if (today == null || today.date != today()) {
            val remote = remoteAstronomyPictureRepository.get(today(), token)
            todayAstronomyPictureRepository.save(remote)
        } else {
            today
        }
    }

    private fun today(): Date {
        return Date()
    }
}