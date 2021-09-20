package by.radiance.space.picrures.ui.list.viewModel

import androidx.lifecycle.*
import by.radiance.space.pictures.domain.presenter.PictureListViewModel
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.presenter.state.PicturesListUiState
import by.radiance.space.pictures.domain.usecase.GetLocalPictureUseCase
import by.radiance.space.pictures.domain.usecase.GetRandomPictureUseCase
import by.radiance.space.pictures.domain.usecase.GetTodayPictureUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

@ExperimentalCoroutinesApi
class PicturesListViewModel(
    private val todayPictureUseCase: GetTodayPictureUseCase,
    private val randomPictureUseCase: GetRandomPictureUseCase,
    private val localPictureUseCase: GetLocalPictureUseCase,
) : PictureListViewModel, ViewModel() {

    private val _today = MutableStateFlow(PictureUiState.Success(null))
    override val today: StateFlow<PictureUiState> = _today

    private val _random = MutableStateFlow(PictureUiState.Success(null))
    override val random: StateFlow<PictureUiState> = _random

    private val _list = MutableStateFlow(PicturesListUiState.Success(emptyList()))
    override val list: StateFlow<PicturesListUiState> = _list

    override fun filter(stateDate: Date, endDate: Date) {

    }

    override fun init() {
        viewModelScope.launch {
            _today.value = PictureUiState.Success(todayPictureUseCase.get())
        }

        viewModelScope.launch {
            _random.value = PictureUiState.Success(randomPictureUseCase.get())
        }

        viewModelScope.launch {
            localPictureUseCase.get().collect { list ->
                _list.value = PicturesListUiState.Success(list)
            }
        }
    }
}