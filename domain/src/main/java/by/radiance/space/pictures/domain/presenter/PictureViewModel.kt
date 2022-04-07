package by.radiance.space.pictures.domain.presenter

import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.presenter.state.QrCodeUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
interface PictureViewModel {
    val qrCode: StateFlow<QrCodeUiState>

    fun save()
    fun picture(id: Id): StateFlow<PictureUiState>
    fun setToBackground()
    fun setToLickScreen()
}