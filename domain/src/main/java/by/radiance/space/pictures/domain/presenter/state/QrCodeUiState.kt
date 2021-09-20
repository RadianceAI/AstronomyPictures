package by.radiance.space.pictures.domain.presenter.state

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.entity.QrCode

sealed class QrCodeUiState {
    data class Success(val qr: QrCode?): QrCodeUiState()
    data class Error(val reason: Throwable?): QrCodeUiState()
}
