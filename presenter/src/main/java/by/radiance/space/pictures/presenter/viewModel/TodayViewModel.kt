package by.radiance.space.pictures.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.radiance.space.pictures.domain.presenter.TodayPictureViewModel
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.usecase.GetAstronomyPicturesUseCase
import by.radiance.space.pictures.domain.utils.LoadingState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date

@ExperimentalCoroutinesApi
class TodayViewModel @ExperimentalCoroutinesApi constructor(
    private val astronomyPictureUseCase: GetAstronomyPicturesUseCase,
): ViewModel(), TodayPictureViewModel {

    override val today = MutableStateFlow<PictureUiState>(PictureUiState.Loading)

    init {
        viewModelScope.launch {
            astronomyPictureUseCase.get(Date(), Date())
                .onEach { pictures ->
                    today.update { _ ->
                        when (pictures) {
                            is LoadingState.Error -> PictureUiState.Error(pictures.throwable)
                            is LoadingState.Success -> PictureUiState.Success(pictures.data.first())
                        }
                    }
                }
                .collect()
        }
    }
}