package by.radiance.space.picrures.presenter.viewModel

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.PictureViewModel
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.presenter.state.QrCodeUiState
import by.radiance.space.pictures.domain.usecase.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

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
                .map { PictureUiState.Success(it) }
                .stateIn(
                    viewModelScope,
                    SharingStarted.WhileSubscribed(),
                    PictureUiState.Success(null),
                )

    override fun save(picture: Picture) {
        viewModelScope.launch {
            likeUseCase.like(picture)
        }
    }

    override fun setToWallpaper(wallpaper: Drawable, flag: Int) {
        viewModelScope.launch {
            wallpaperUseCase.set(wallpaper, flag)
        }
    }

    override fun share(image: Drawable) {
        viewModelScope.launch {
            shareUseCase.share(image)
        }
    }
}