package by.radiance.space.picrures.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.presenter.PictureViewModel
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.presenter.state.QrCodeUiState
import by.radiance.space.pictures.domain.usecase.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class DetailsViewModel(
    private val getLocalPictureUseCase: GetLocalPictureUseCase,
    private val getRandomPictureUseCase: GetRandomPictureUseCase,
    private val getTodayPictureUseCase: GetTodayPictureUseCase,
    private val savePictureUseCase: SavePictureUseCase,
    private val deletePictureUseCase: DeletePictureUseCase,
): ViewModel(), PictureViewModel {

    private val _picture = MutableStateFlow<PictureUiState>(PictureUiState.Success(null))
    override val picture: StateFlow<PictureUiState> = _picture

    private val _qrCode = MutableStateFlow<QrCodeUiState>(QrCodeUiState.Success(null))
    override val qrCode: StateFlow<QrCodeUiState> = _qrCode

    override fun init(id: Id) {
        viewModelScope.launch {
            if (id.isToday) {
                _picture.value = PictureUiState.Success(getTodayPictureUseCase.get())
            } else {
                val random = getRandomPictureUseCase.get()
                if (random.id == id) {
                    _picture.value = PictureUiState.Success(random)
                } else {
                    getLocalPictureUseCase.get(id.date)
                        .onEach { picture ->
                            _picture.value = PictureUiState.Success(picture)
                        }.collect()
                }
            }
        }
    }

    override fun save() {
        TODO("not implemented")
    }

    override fun setToBackground() {
        TODO("not implemented")
    }

    override fun setToLickScreen() {
        TODO("not implemented")
    }
}