package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.LocalRepository
import by.radiance.space.pictures.domain.repository.RemoteRepository
import by.radiance.space.pictures.domain.repository.TempRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class GetRandomPictureUseCase(
    private val tempRepository: TempRepository,
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
) {
    fun get(): Flow<Picture> = flow {
        val savedPicture = tempRepository.getAll()
            .first()
            .firstOrNull { picture -> !picture.id.isToday }
            ?.let {
                it.copy(id = it.id.copy(isRandom = true))
            }

        val randomPicture =
            savedPicture ?: remoteRepository.getRandomPicture(1).first().also { picture ->
                tempRepository.save(picture)
            }

        emit(randomPicture)
        localRepository.getPicture(randomPicture.id.date)
            .onEach { picture ->
                if (picture == null)
                    emit(randomPicture)
                else
                    emit(picture.copy(id = picture.id.copy(isRandom = true)))
            }
            .collect()
    }
}