package by.radiance.space.pictures.domain.presenter.state

import by.radiance.space.pictures.domain.entity.Picture

sealed class PictureUiState {
    data class Success(val picture: Picture?): PictureUiState()
    data class Error(val reason: Throwable?): PictureUiState()
}
