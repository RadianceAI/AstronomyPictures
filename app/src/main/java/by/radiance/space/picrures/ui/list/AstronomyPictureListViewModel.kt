package by.radiance.space.picrures.ui.list

import androidx.lifecycle.*
import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.usecase.saved.SavedAstronomyPicturesUseCase
import by.radiance.space.pictures.domain.usecase.today.TodayAstronomyPictureUseCase
import kotlinx.coroutines.launch

class AstronomyPictureListViewModel(
    private val savedAstronomyPicturesUseCase: SavedAstronomyPicturesUseCase,
    private val todayAstronomyPictureUseCase: TodayAstronomyPictureUseCase,
): ListPictureViewModel, ViewModel() {
    private val _todayPicture: MutableLiveData<AstronomyPicture> = MutableLiveData()
    private val _pictures: MutableLiveData<List<AstronomyPicture>> = MutableLiveData()

    override val pictures: LiveData<List<AstronomyPicture>> = MediatorLiveData<List<AstronomyPicture>>().apply {
        addSource(_todayPicture) {
            val current = mutableListOf(it)
            current.addAll(_pictures.value?: emptyList())
            postValue(current)
        }
        addSource(_pictures) {
            val current = it.toMutableList()
            _todayPicture.value?.let {
                current.add(0, it)
            }
            postValue(current)
        }
    }

    override fun init() {
        viewModelScope.launch {
            _pictures.postValue(savedAstronomyPicturesUseCase.get())
        }
        viewModelScope.launch {
            _todayPicture.postValue(todayAstronomyPictureUseCase.get())
        }
    }
}