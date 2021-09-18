package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.RemoteRepository
import by.radiance.space.pictures.domain.repository.TempRepository
import kotlinx.coroutines.flow.first
import java.util.*

class GetTodayPictureUseCase(
    private val tempRepository: TempRepository,
    private val remoteRepository: RemoteRepository,
) {
    suspend fun get(): Picture {
        val savedPicture = tempRepository.getAll().first()
            .firstOrNull { picture ->
                picture.id.isToday
            }

        return savedPicture?:remoteRepository.getPicture(Date()).also { picture ->
            tempRepository.save(picture)
        }
    }
}