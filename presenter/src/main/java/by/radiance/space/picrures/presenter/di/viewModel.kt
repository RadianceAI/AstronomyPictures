package by.radiance.space.picrures.presenter.di

import by.radiance.space.picrures.presenter.viewModel.ListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { ListViewModel(get(), get(), get()) }
}