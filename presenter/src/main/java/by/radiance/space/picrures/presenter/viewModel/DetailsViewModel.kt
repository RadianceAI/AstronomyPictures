package by.radiance.space.picrures.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.radiance.space.picrures.presenter.utils.toUiState
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
    private val getPictureUseCase: GetPictureUseCase,
    private val savePictureUseCase: SavePictureUseCase,
    private val deletePictureUseCase: DeletePictureUseCase,
): ViewModel(), PictureViewModel {

    private val _picture = MutableStateFlow<PictureUiState>(PictureUiState.Success(null))
    override val picture: StateFlow<PictureUiState> = _picture

    private val _qrCode = MutableStateFlow<QrCodeUiState>(QrCodeUiState.Success(null))
    override val qrCode: StateFlow<QrCodeUiState> = _qrCode

    override fun init(id: Id) {
        viewModelScope.launch {
            getPictureUseCase.get(id)
                .onEach { picture ->
                    _picture.value = picture.toUiState()
                }
                .collect()
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