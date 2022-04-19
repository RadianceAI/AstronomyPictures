package by.radiance.space.picrures.presenter.viewModel

import android.util.Log
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
    private val likeUseCase: LikeUseCase,
) : PictureListViewModel, ViewModel() {

    private val _today = MutableStateFlow(PictureUiState.Success(null))
    override val today: StateFlow<PictureUiState> = _today

    private val _random = MutableStateFlow(PictureUiState.Success(null))
    override val random: StateFlow<PictureUiState> = _random

    private val _list = MutableStateFlow(PicturesListUiState.Success(mapOf()))
    override val list: StateFlow<PicturesListUiState> = _list

    override fun filter(stateDate: Date, endDate: Date) {

    }

    override fun save(picture: Picture) {
        viewModelScope.launch {
            likeUseCase.like(picture)
        }
    }

    init {
        viewModelScope.launch {
            todayPictureUseCase.get()
                .onEach { picture ->
                    _today.value = PictureUiState.Success(picture)
                }
                .collect()
        }

        viewModelScope.launch {
            randomPictureUseCase.get()
                .onEach { picture ->
                    _random.value = PictureUiState.Success(picture)
                }
                .collect()
        }

        viewModelScope.launch {
            localPictureUseCase.get()
                .onEach { list ->
                    _list.value = PicturesListUiState.Success(list)
                }
                .collect()
        }
    }
    override fun init() {

    }
}