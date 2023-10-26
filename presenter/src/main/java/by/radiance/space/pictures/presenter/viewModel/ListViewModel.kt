package by.radiance.space.pictures.presenter.viewModel

import androidx.lifecycle.*
import by.radiance.space.pictures.domain.presenter.PictureListViewModel
import by.radiance.space.pictures.domain.presenter.state.PicturesListUiState
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
    private val localPictureUseCase: GetLocalPictureUseCase,
) : PictureListViewModel, ViewModel() {

    private val _list = MutableStateFlow(PicturesListUiState.Success(mapOf()))
    override val list: StateFlow<PicturesListUiState> = _list

    override fun filter(stateDate: Date, endDate: Date) {

    }

    init {
        viewModelScope.launch {
            localPictureUseCase.get()
                .onEach { list ->
                    _list.value = PicturesListUiState.Success(list)
                }
                .collect()
        }
    }
}