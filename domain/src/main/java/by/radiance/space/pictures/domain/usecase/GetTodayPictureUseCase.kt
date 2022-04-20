package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.LocalRepository
import by.radiance.space.pictures.domain.repository.RemoteRepository
import by.radiance.space.pictures.domain.repository.TempRepository
import by.radiance.space.pictures.domain.utils.LoadingState
import by.radiance.space.pictures.domain.utils.asState
import kotlinx.coroutines.flow.*
import java.lang.Exception
import java.util.*

class GetTodayPictureUseCase(
    private val tempRepository: TempRepository,
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
) {
    fun get(): Flow<LoadingState<Picture>> = flow {
        val savedPicture = tempRepository.getAll().first()
            .firstOrNull { picture ->
                picture.id.isToday
            }

        try {
            val todayPicture = savedPicture ?: remoteRepository.getPicture(Date()).also { picture ->
                tempRepository.save(picture)
            }

            emit(todayPicture.asState())
            localRepository.getPicture(todayPicture.id.date)
                .onEach { picture ->
                    if (picture == null)
                        emit(todayPicture.asState())
                    else
                        emit(picture.asState())
                }
                .collect()
        } catch (e: Exception) {
            emit(LoadingState.Error<Picture>(e))
        }
    }
}