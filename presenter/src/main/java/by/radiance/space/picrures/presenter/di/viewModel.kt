package by.radiance.space.picrures.presenter.di

import by.radiance.space.picrures.presenter.viewModel.DetailsViewModel
import by.radiance.space.picrures.presenter.viewModel.ListViewModel
import by.radiance.space.picrures.presenter.viewModel.NewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { ListViewModel(get()) }
    viewModel { DetailsViewModel(get(), get(), get(), get())}
    viewModel { NewViewModel(get(), get(), get()) }
}