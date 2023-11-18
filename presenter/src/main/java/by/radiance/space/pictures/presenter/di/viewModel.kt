package by.radiance.space.pictures.presenter.di

import by.radiance.space.pictures.presenter.viewModel.DetailsViewModel
import by.radiance.space.pictures.presenter.viewModel.ListViewModel
import by.radiance.space.pictures.presenter.viewModel.TodayViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { ListViewModel() }
    viewModel { DetailsViewModel(get(), get())}
    viewModel { TodayViewModel(get()) }
}