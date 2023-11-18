package by.radiance.space.pictures.domain.presenter.state

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.utils.LoadingState

sealed class PictureUiState {
    data object Loading : PictureUiState()
    data class Success(val picture: Picture): PictureUiState()
    data class Error(val reason: Throwable?): PictureUiState()
}

fun LoadingState<Picture>.asUiState() = when (this) {
    is LoadingState.Success -> PictureUiState.Success(this.data)
    is LoadingState.Error -> PictureUiState.Error(this.throwable)
}