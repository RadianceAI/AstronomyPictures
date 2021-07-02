package by.radiance.space.picrures.ui.list.viewModel

import androidx.lifecycle.*
import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.presenter.PictureListViewModel
import by.radiance.space.pictures.domain.usecase.saved.SavedAstronomyPicturesUseCase
import by.radiance.space.pictures.domain.usecase.today.TodayAstronomyPictureUseCase
import kotlinx.coroutines.launch

class AstronomyPictureListViewModel(
    private val savedAstronomyPicturesUseCase: SavedAstronomyPicturesUseCase,
): PictureListViewModel, ViewModel() {
    private val _astronomyPictureList: MutableLiveData<List<AstronomyPicture>> = MutableLiveData()
    override val astronomyPictureList: LiveData<List<AstronomyPicture>> = _astronomyPictureList

    override fun init() {
        viewModelScope.launch {
            _astronomyPictureList.postValue(savedAstronomyPicturesUseCase.get())
        }
    }
}