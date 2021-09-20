package by.radiance.space.pictures.domain.presenter.state

import by.radiance.space.pictures.domain.entity.Picture

sealed class PicturesListUiState {
    data class Success(val pictures: List<Picture>): PicturesListUiState()
    data class Error(val reason: Throwable?): PicturesListUiState()
}
