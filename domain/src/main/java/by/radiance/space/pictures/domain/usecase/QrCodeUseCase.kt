package by.radiance.space.pictures.domain.usecase

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.entity.QrCode
import by.radiance.space.pictures.domain.repository.QrCodeRepository

class QrCodeUseCase(
    private val qrCodeRepository: QrCodeRepository,
) {
    suspend fun get(picture: Picture): QrCode {
        return qrCodeRepository.getQrCode(picture)
    }
}