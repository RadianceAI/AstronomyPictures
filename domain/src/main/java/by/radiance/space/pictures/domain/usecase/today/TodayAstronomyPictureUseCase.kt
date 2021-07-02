package by.radiance.space.pictures.domain.usecase.today

import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.repository.token.TokenRepository
import by.radiance.space.pictures.domain.repository.remote.RemoteAstronomyPictureRepository
import by.radiance.space.pictures.domain.repository.saved.SavedAstronomyPicturesRepository
import by.radiance.space.pictures.domain.repository.today.TodayAstronomyPictureRepository
import java.util.*

class TodayAstronomyPictureUseCase(
    private val tokenRepository: TokenRepository,
    private val remoteAstronomyPictureRepository: RemoteAstronomyPictureRepository,
    private val todayAstronomyPictureRepository: TodayAstronomyPictureRepository,
    private val savedAstronomyPicturesRepository: SavedAstronomyPicturesRepository,
) {
    suspend fun get(): AstronomyPicture {
        val token = tokenRepository.get()

        val savedToday = todayAstronomyPictureRepository.get()

        val today = if (savedToday == null || !savedToday.id.isToday) {
            val remote = remoteAstronomyPictureRepository.get(today(), token)
            todayAstronomyPictureRepository.saveTodayPicture(remote)
        } else {
            savedToday
        }

        val saved = savedAstronomyPicturesRepository.getSavedPictureById(today.id)

        return today.copy(
                isSaved = saved != null
        )
    }

    private fun today(): Date {
        return Date()
    }
}