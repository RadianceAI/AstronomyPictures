package by.radiance.space.pictures.domain.presenter

import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.presenter.state.QrCodeUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
interface PictureViewModel {
    val picture: StateFlow<PictureUiState>
    val qrCode: StateFlow<QrCodeUiState>

    fun init(id: Id)
    fun save()
    fun setToBackground()
    fun setToLickScreen()
}