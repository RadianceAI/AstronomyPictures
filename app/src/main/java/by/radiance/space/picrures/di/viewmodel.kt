package by.radiance.space.picrures.di

import by.radiance.space.picrures.ui.list.viewModel.AstronomyPictureListViewModel
import by.radiance.space.picrures.ui.list.viewModel.RandomAstronomyPictureViewModel
import by.radiance.space.picrures.ui.list.viewModel.TodayAstronomyPictureViewModel
import by.radiance.space.picrures.ui.picture.viewModel.AstronomyPictureViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { AstronomyPictureListViewModel(get()) }
    viewModel { AstronomyPictureViewModel(get(), get(), get()) }
    viewModel { RandomAstronomyPictureViewModel(get()) }
    viewModel { TodayAstronomyPictureViewModel(get()) }
}