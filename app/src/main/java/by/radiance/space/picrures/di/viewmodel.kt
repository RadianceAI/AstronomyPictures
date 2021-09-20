package by.radiance.space.picrures.di

import by.radiance.space.picrures.ui.list.viewModel.PicturesListViewModel
import by.radiance.space.picrures.ui.picture.viewModel.AstronomyPictureViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val viewModel = module {
    viewModel { PicturesListViewModel(get(), get(), get()) }
    viewModel { AstronomyPictureViewModel() }
}