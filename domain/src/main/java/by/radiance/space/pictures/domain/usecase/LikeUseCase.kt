package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.repository.LocalRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first

class LikeUseCase(
    private val deletePictureUseCase: DeletePictureUseCase,
    private val savePictureUseCase: SavePictureUseCase,
    private val localRepository: LocalRepository
) {

    suspend fun like(picture: Picture) {
        val local = localRepository.getPicture(picture.id.date)
            .first()

        if (local == null)
            savePictureUseCase.save(picture)
        else
            deletePictureUseCase.delete(picture)
    }
}