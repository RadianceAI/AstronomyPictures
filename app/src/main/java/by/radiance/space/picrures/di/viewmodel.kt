package by.radiance.space.picrures.di

import by.radiance.space.picrures.ui.list.AstronomyPictureListViewModel
import by.radiance.space.picrures.ui.picture.AstronomyPictureViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { AstronomyPictureListViewModel(get(), get()) }
    viewModel { AstronomyPictureViewModel(get(), get()) }
}