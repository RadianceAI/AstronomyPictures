package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import java.util.*

class GetLocalPictureUseCase(
    private val localRepository: LocalRepository,
) {
    fun get(): Flow<List<Picture>> {
        return localRepository.getAll()
    }

    fun get(id: Id): Flow<Picture> {
        return localRepository.getPicture(id.date)
    }
}