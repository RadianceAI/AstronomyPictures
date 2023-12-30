package by.radiance.space.pictures.presenter.viewModel

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.PictureViewModel
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.usecase.*
import by.radiance.space.pictures.domain.utils.DateUtil
import by.radiance.space.pictures.domain.utils.LoadingState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.Date

@ExperimentalCoroutinesApi
class DetailsViewModel(
    private val getPictureUseCase: GetAstronomyPicturesUseCase,
    private val wallpaperUseCase: SetWallpaperUseCase,
): ViewModel(), PictureViewModel {

    private val _progress = MutableStateFlow(false)
    override val progress: StateFlow<Boolean> = _progress

    override fun picture(id: Id): StateFlow<PictureUiState> =
            getPictureUseCase.get(DateUtil.parseId(id.date)!!, DateUtil.parseId(id.date)!!)
                .map { pictures ->
                    when (pictures) {
                        is LoadingState.Error -> PictureUiState.Error(pictures.throwable)
                        is LoadingState.Success -> PictureUiState.Success(pictures.data.first())
                    }
                }
                .stateIn(
                    viewModelScope,
                    SharingStarted.WhileSubscribed(),
                    PictureUiState.Loading,
                )

    override fun save(picture: Picture) {
        viewModelScope.launch {
        }
    }

    override fun setSystemWallpaper(wallpaper: Drawable) {
        viewModelScope.launch(Dispatchers.IO) {
            progress { wallpaperUseCase.setSystemWallpaper(wallpaper) }
        }
    }

    override fun setLockScreenWallpaper(wallpaper: Drawable) {
        viewModelScope.launch(Dispatchers.IO) {
            progress { wallpaperUseCase.setLockScreenWallpaper(wallpaper) }
        }
    }

    override fun setAllWallpaper(wallpaper: Drawable) {
        viewModelScope.launch(Dispatchers.IO) {
            progress { wallpaperUseCase.setAllWallpaper(wallpaper) }
        }
    }

    override fun share(image: Drawable) {
        viewModelScope.launch(Dispatchers.IO) {
            progress {  }
        }
    }

    private fun progress(action: () -> Unit) {
        _progress.update { true }
        action()
        _progress.update { false }
    }
}