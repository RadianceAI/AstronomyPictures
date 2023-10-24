package by.radiance.space.picrures.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.NewPicturesViewModel
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.presenter.state.asUiState
import by.radiance.space.pictures.domain.usecase.GetAstronomyPicturesUseCase
import by.radiance.space.pictures.domain.usecase.LikeUseCase
import by.radiance.space.pictures.domain.utils.LoadingState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Date

@ExperimentalCoroutinesApi
class NewViewModel @ExperimentalCoroutinesApi constructor(
    private val astronomyPictureUseCase: GetAstronomyPicturesUseCase,
    private val likeUseCase: LikeUseCase,
): ViewModel(), NewPicturesViewModel {

    private val _today = MutableStateFlow<PictureUiState>(PictureUiState.Loading)
    override val today: StateFlow<PictureUiState> = _today

    private val _random = MutableStateFlow<PictureUiState>(PictureUiState.Loading)
    override val random: StateFlow<PictureUiState> = _random


    override fun save(picture: Picture) {
        viewModelScope.launch {
            likeUseCase.like(picture)
        }
    }

    init {
        viewModelScope.launch {
            astronomyPictureUseCase.get(Date(), Date())
                .onEach { pictures ->
                    _today.value = when (pictures) {
                        is LoadingState.Error -> PictureUiState.Error(pictures.throwable)
                        is LoadingState.Success -> PictureUiState.Success(pictures.data.first())
                    }
                }
                .collect()
        }
    }
}