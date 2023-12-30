
package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.RemoteAstronomyPictureRepository
import by.radiance.space.pictures.domain.utils.LoadingState
import by.radiance.space.pictures.domain.utils.asState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import java.util.Date

class GetAstronomyPicturesUseCase(
    private val remoteRepository: RemoteAstronomyPictureRepository,
) {
    fun get(date: Date): Flow<LoadingState<Picture>> = channelFlow {
        try {
            val pictures = remoteRepository.getPictures(date, date)
            if (pictures.size == 1) {
                send(pictures[0].asState())
            } else {
                send(LoadingState.Error(Throwable("No items find")))
            }
        } catch (e: Exception) {
            send(LoadingState.Error(e))
        } finally {
            close()
        }
    }

    fun get(startDate: Date, endDate: Date): Flow<LoadingState<List<Picture>>> = channelFlow {
        try {
            val pictures = remoteRepository.getPictures(startDate, endDate)
            send(pictures.asState())
        } catch (e: Exception) {
            send(LoadingState.Error(e))
        } finally {
            close()
        }
    }
}