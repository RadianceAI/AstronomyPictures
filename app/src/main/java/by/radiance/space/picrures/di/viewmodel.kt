package by.radiance.space.picrures.di

import by.radiance.space.picrures.ui.picture.AstronomyPictureViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { AstronomyPictureViewModel(get(), get()) }
}