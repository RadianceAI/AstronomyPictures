package by.radiance.space.picrures.presenter.viewModel

import androidx.lifecycle.*
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.PictureListViewModel
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.presenter.state.PicturesListUiState
import by.radiance.space.pictures.domain.presenter.state.QrCodeUiState
import by.radiance.space.pictures.domain.usecase.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*

@ExperimentalCoroutinesApi
class ListViewModel(
    private val todayPictureUseCase: GetTodayPictureUseCase,
    private val randomPictureUseCase: GetRandomPictureUseCase,
    private val localPictureUseCase: GetLocalPictureUseCase,
    private val savePictureUseCase: SavePictureUseCase,
    private val deletePictureUseCase: DeletePictureUseCase,
) : PictureListViewModel, ViewModel() {

    private val _today = MutableStateFlow(PictureUiState.Success(null))
    override val today: StateFlow<PictureUiState> = _today

    private val _random = MutableStateFlow(PictureUiState.Success(null))
    override val random: StateFlow<PictureUiState> = _random

    private val _list = MutableStateFlow(PicturesListUiState.Success(emptyList()))
    override val list: StateFlow<PicturesListUiState> = _list

    override fun filter(stateDate: Date, endDate: Date) {

    }

    override fun save(picture: Picture) {
        if (list.value is PicturesListUiState.Success) {
            viewModelScope.launch {
                if ((list.value as PicturesListUiState.Success)
                        .pictures.find { p -> p.id == picture.id } != null
                ) {
                    deletePictureUseCase.delete(picture)
                } else {
                    savePictureUseCase.save(picture)
                }
            }
        }
    }

    override fun init() {
        viewModelScope.launch {
            _today.value = PictureUiState.Success(todayPictureUseCase.get())
        }

        viewModelScope.launch {
            _random.value = PictureUiState.Success(randomPictureUseCase.get())
        }

        viewModelScope.launch {
            localPictureUseCase.get()
                .onEach { list ->
                    _list.value = PicturesListUiState.Success(list)
                }
                .collect()
        }
    }
}