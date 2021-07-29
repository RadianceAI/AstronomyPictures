package by.radiance.space.picrures.ui.list.viewModel

import androidx.lifecycle.*
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.PictureListViewModel
import by.radiance.space.pictures.domain.usecase.saved.SavedAstronomyPicturesUseCase
import kotlinx.coroutines.launch

class AstronomyPictureListViewModel(
    private val savedAstronomyPicturesUseCase: SavedAstronomyPicturesUseCase,
): PictureListViewModel, ViewModel() {
    private val _PictureList: MutableLiveData<List<Picture>> = MutableLiveData()
    override val pictureList: LiveData<List<Picture>> = _PictureList

    override fun init() {
        viewModelScope.launch {
            _PictureList.postValue(savedAstronomyPicturesUseCase.get())
        }
    }
}