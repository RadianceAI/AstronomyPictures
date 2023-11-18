package by.radiance.space.pictures.domain.presenter

import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
interface TodayPictureViewModel {
    val today: StateFlow<PictureUiState>
}