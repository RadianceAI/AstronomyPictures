package by.radiance.space.pictures.domain.repository

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.entity.QrCode

interface QrCodeRepository {
    suspend fun getQrCode(picture: Picture): QrCode
}