package by.radiance.space.pictures.presenter.di

import by.radiance.space.pictures.presenter.viewModel.AboutViewModel
import by.radiance.space.pictures.presenter.viewModel.DetailsViewModel
import by.radiance.space.pictures.presenter.viewModel.GalleryViewModel
import by.radiance.space.pictures.presenter.viewModel.ListViewModel
import by.radiance.space.pictures.presenter.viewModel.TodayViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val viewModel = module {
    viewModel { ListViewModel() }
    viewModel { DetailsViewModel(get(), get())}
    viewModel { TodayViewModel(get()) }
    viewModel { GalleryViewModel(get()) }
    viewModel { AboutViewModel() }
}