package by.radiance.space.pictures.domain.usecase

import android.util.Log
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.LocalRepository
import by.radiance.space.pictures.domain.repository.RemoteRepository
import by.radiance.space.pictures.domain.repository.TempRepository
import kotlinx.coroutines.flow.*
import java.lang.Exception
import java.util.*

class GetTodayPictureUseCase(
    private val tempRepository: TempRepository,
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
) {
    fun get(): Flow<Picture> = flow {
        val savedPicture = tempRepository.getAll().first()
            .firstOrNull { picture ->
                picture.id.isToday
            }

        try {
            val todayPicture = savedPicture ?: remoteRepository.getPicture(Date()).also { picture ->
                tempRepository.save(picture)
            }
            throw Exception()

            emit(todayPicture)
            localRepository.getPicture(todayPicture.id.date)
                .onEach { picture ->
                    if (picture == null)
                        emit(todayPicture)
                    else
                        emit(picture)
                }
                .collect()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}