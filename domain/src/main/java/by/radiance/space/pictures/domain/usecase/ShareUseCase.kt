package by.radiance.space.pictures.domain.usecase

import android.graphics.drawable.Drawable
import by.radiance.space.pictures.domain.repository.ShareRepository

class ShareUseCase(
    private val shareRepository: ShareRepository
) {

    fun share(image: Drawable) {
        shareRepository.share(image)
    }
}