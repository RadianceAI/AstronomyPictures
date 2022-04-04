package by.radiance.space.picrures.presenter.di

import androidx.lifecycle.viewmodel.compose.viewModel
import by.radiance.space.picrures.presenter.viewModel.DetailsViewModel
import by.radiance.space.picrures.presenter.viewModel.ListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { ListViewModel(get(), get(), get(), get(), get()) }
    viewModel { DetailsViewModel(get(), get(), get(), get(), get())}
}