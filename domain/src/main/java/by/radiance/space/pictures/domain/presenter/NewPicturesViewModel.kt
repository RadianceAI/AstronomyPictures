package by.radiance.space.pictures.domain.presenter

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
interface NewPicturesViewModel {
    val today: StateFlow<PictureUiState>
    val random: StateFlow<PictureUiState>

    fun save(picture: Picture)
}