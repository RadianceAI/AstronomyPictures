package by.radiance.space.picrures.presenter.viewModel

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.PictureViewModel
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.presenter.state.QrCodeUiState
import by.radiance.space.pictures.domain.presenter.state.asUiState
import by.radiance.space.pictures.domain.usecase.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class DetailsViewModel(
    private val getPictureUseCase: GetPictureUseCase,
    private val likeUseCase: LikeUseCase,
    private val shareUseCase: ShareUseCase,
    private val wallpaperUseCase: SetWallpaperUseCase,
): ViewModel(), PictureViewModel {

    private val _qrCode = MutableStateFlow<QrCodeUiState>(QrCodeUiState.Success(null))
    override val qrCode: StateFlow<QrCodeUiState> = _qrCode

    override fun picture(id: Id): StateFlow<PictureUiState> =
            getPictureUseCase.get(id)
                .map { it.asUiState() }
                .stateIn(
                    viewModelScope,
                    SharingStarted.WhileSubscribed(),
                    PictureUiState.Loading,
                )

    override fun save(picture: Picture) {
        viewModelScope.launch {
            likeUseCase.like(picture)
        }
    }

    override fun setSystemWallpaper(wallpaper: Drawable) {
        viewModelScope.launch(Dispatchers.IO) {
            wallpaperUseCase.setSystemWallpaper(wallpaper)
        }
    }

    override fun setLockScreenWallpaper(wallpaper: Drawable) {
        viewModelScope.launch(Dispatchers.IO) {
            wallpaperUseCase.setLockScreenWallpaper(wallpaper)
        }
    }

    override fun setAllWallpaper(wallpaper: Drawable) {
        viewModelScope.launch(Dispatchers.IO) {
            wallpaperUseCase.setAllWallpaper(wallpaper)
        }
    }

    override fun share(image: Drawable) {
        viewModelScope.launch(Dispatchers.IO) {
            shareUseCase.share(image)
        }
    }
}