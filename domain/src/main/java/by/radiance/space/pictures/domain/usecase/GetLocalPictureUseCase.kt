package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import java.util.*

class GetLocalPictureUseCase(
    private val localRepository: LocalRepository,
) {
    suspend fun get(): Flow<List<Picture>> {
        return localRepository.getAll()
    }

    suspend fun get(date: Date): Picture {
        return localRepository.getPicture(date)
    }
}