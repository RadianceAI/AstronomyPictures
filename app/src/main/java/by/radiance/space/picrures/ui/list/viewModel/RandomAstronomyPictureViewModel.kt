package by.radiance.space.picrures.ui.list.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.presenter.RandomPictureViewModel
import by.radiance.space.pictures.domain.usecase.random.RandomAstronomyPictureUseCase
import kotlinx.coroutines.launch

class RandomAstronomyPictureViewModel(
    private val randomAstronomyPictureUseCase: RandomAstronomyPictureUseCase,
) : ViewModel(), RandomPictureViewModel {
    private val _randomPicture = MutableLiveData<AstronomyPicture>()
    override val randomPicture: LiveData<AstronomyPicture> = _randomPicture

    override fun init() {
        viewModelScope.launch {
            _randomPicture.postValue(randomAstronomyPictureUseCase.get())
        }
    }
}