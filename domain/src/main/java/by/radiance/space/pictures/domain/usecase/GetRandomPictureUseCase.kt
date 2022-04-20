package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.LocalRepository
import by.radiance.space.pictures.domain.repository.RemoteRepository
import by.radiance.space.pictures.domain.repository.TempRepository
import by.radiance.space.pictures.domain.utils.LoadingState
import by.radiance.space.pictures.domain.utils.asState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import java.lang.Exception

@ExperimentalCoroutinesApi
class GetRandomPictureUseCase(
    private val tempRepository: TempRepository,
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
) {
    fun get(): Flow<LoadingState<Picture>> = flow {
        val savedPicture = tempRepository.getAll()
            .first()
            .firstOrNull { picture -> !picture.id.isToday }
            ?.let {
                it.copy(id = it.id.copy(isRandom = true))
            }

        try {
            val randomPicture =
                savedPicture ?: remoteRepository.getRandomPicture(1).first().also { picture ->
                    tempRepository.save(picture)
                }

            emit(randomPicture.asState())
            localRepository.getPicture(randomPicture.id.date)
                .onEach { picture ->
                    if (picture == null)
                        emit(randomPicture.asState())
                    else
                        emit(picture.copy(id = picture.id.copy(isRandom = true)).asState())
                }
                .collect()
        } catch (e: Exception) {
            emit(LoadingState.Error<Picture>(e))
        }
    }
}