package by.radiance.space.pictures.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class ListViewModel(
) : ViewModel() {

    init {
        viewModelScope.launch {
        }
    }
}