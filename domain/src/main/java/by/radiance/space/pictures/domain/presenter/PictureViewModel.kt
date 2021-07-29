package by.radiance.space.pictures.domain.presenter

import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Picture
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
interface PictureViewModel {
    val picture: StateFlow<Picture>
    val qrCode: StateFlow<Picture>

    fun init(id: Id)
    fun save()
    fun setToBackground()
    fun setToLickScreen()
}