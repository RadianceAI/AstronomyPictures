package by.radiance.space.pictures.domain.presenter.state

import by.radiance.space.pictures.domain.entity.Group
import by.radiance.space.pictures.domain.entity.Picture
import java.util.*

sealed class PicturesListUiState {
    data class Success(val pictures: Map<Group, List<Picture>>): PicturesListUiState()
    data class Error(val reason: Throwable?): PicturesListUiState()
}
