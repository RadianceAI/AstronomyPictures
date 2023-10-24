
package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.LocalRepository
import by.radiance.space.pictures.domain.repository.RemoteAstronomyPictureRepository
import by.radiance.space.pictures.domain.repository.TempRepository
import by.radiance.space.pictures.domain.utils.LoadingState
import by.radiance.space.pictures.domain.utils.asState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import java.lang.Exception
import java.util.*

class GetAstronomyPicturesUseCase(
    private val remoteRepository: RemoteAstronomyPictureRepository,
) {
    fun get(startDate: Date, endDate: Date): Flow<LoadingState<List<Picture>>> = channelFlow {
        try {
            val pictures = remoteRepository.getPictures(startDate, endDate)
            send(pictures.asState())

        } catch (e: Exception) {
            send(LoadingState.Error(e))
        }
    }
}