package by.radiance.space.picrures.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.NewPicturesViewModel
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.usecase.GetRandomPictureUseCase
import by.radiance.space.pictures.domain.usecase.GetTodayPictureUseCase
import by.radiance.space.pictures.domain.usecase.LikeUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class NewViewModel @ExperimentalCoroutinesApi constructor(
    private val todayPictureUseCase: GetTodayPictureUseCase,
    private val randomPictureUseCase: GetRandomPictureUseCase,
    private val likeUseCase: LikeUseCase,
): ViewModel(), NewPicturesViewModel {

    private val _today = MutableStateFlow(PictureUiState.Success(null))
    override val today: StateFlow<PictureUiState> = _today

    private val _random = MutableStateFlow(PictureUiState.Success(null))
    override val random: StateFlow<PictureUiState> = _random


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
    }
}