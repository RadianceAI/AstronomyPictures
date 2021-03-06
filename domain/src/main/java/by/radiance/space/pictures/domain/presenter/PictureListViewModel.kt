package by.radiance.space.pictures.domain.presenter

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.presenter.state.PicturesListUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import java.util.*

@ExperimentalCoroutinesApi
interface PictureListViewModel {
    val list: StateFlow<PicturesListUiState>

    fun filter(stateDate: Date, endDate: Date)
}