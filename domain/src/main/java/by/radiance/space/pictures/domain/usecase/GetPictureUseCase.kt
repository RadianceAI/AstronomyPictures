package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.LocalRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapNotNull

@ExperimentalCoroutinesApi
class GetPictureUseCase(
    private val localRepository: LocalRepository,
    private val getRandomPictureUseCase: GetRandomPictureUseCase,
    private val getTodayPictureUseCase: GetTodayPictureUseCase,
) {

    fun get(id: Id): Flow<Picture> {
        return when {
            id.isToday -> {
                getTodayPictureUseCase.get()
            }
            id.isRandom -> {
                getRandomPictureUseCase.get()
            }
            else -> {
                localRepository.getPicture(id.date).filterNotNull()
            }
        }
    }
}