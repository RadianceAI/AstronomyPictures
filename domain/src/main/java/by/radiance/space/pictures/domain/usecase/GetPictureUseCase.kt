package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.LocalRepository
import by.radiance.space.pictures.domain.utils.LoadingState
import by.radiance.space.pictures.domain.utils.asState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class GetPictureUseCase(
    private val localRepository: LocalRepository,
    private val getRandomPictureUseCase: GetRandomPictureUseCase,
    private val getTodayPictureUseCase: GetTodayPictureUseCase,
) {

    fun get(id: Id): Flow<LoadingState<Picture>> {
        return when {
            id.isToday -> {
                getTodayPictureUseCase.get()
            }
            id.isRandom -> {
                getRandomPictureUseCase.get()
            }
            else -> {
                localRepository.getPicture(id.date).filterNotNull().map { it.asState() }
            }
        }
    }
}