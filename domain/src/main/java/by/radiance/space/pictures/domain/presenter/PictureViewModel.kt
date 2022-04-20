package by.radiance.space.pictures.domain.presenter

import android.graphics.drawable.Drawable
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.presenter.state.QrCodeUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
interface PictureViewModel {
    val qrCode: StateFlow<QrCodeUiState>
    val progress: StateFlow<Boolean>

    fun save(picture: Picture)
    fun picture(id: Id): StateFlow<PictureUiState>
    fun share(image: Drawable)
    fun setSystemWallpaper(wallpaper: Drawable)
    fun setLockScreenWallpaper(wallpaper: Drawable)
    fun setAllWallpaper(wallpaper: Drawable)
}