package by.radiance.space.pictures.presenter.di

import by.radiance.space.pictures.presenter.viewModel.DetailsViewModel
import by.radiance.space.pictures.presenter.viewModel.GalleryViewModel
import by.radiance.space.pictures.presenter.viewModel.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { DetailsViewModel(get(), get())}
    viewModel { GalleryViewModel(get()) }
    viewModel { SettingsViewModel() }
}