package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.LocalRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow

class GetPictureUseCase(
    private val localRepository: LocalRepository,
    private val getRandomPictureUseCase: GetRandomPictureUseCase,
    private val getTodayPictureUseCase: GetTodayPictureUseCase,
) {

    fun get(id: Id): Flow<Picture> {
        return when {
            id.isToday -> {
                flow {
                    getTodayPictureUseCase.get()
                }
            }
            id.isRandom -> {
                flow {
                    getRandomPictureUseCase.get()
                }
            }
            else -> {
                localRepository.getPicture(id.date)
            }
        }
    }
}