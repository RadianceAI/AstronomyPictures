package by.radiance.space.pictures.domain.di

import by.radiance.space.pictures.domain.usecase.save.SaveAstronomyPictureUseCase
import by.radiance.space.pictures.domain.usecase.saved.SavedAstronomyPicturesUseCase
import by.radiance.space.pictures.domain.usecase.today.TodayAstronomyPictureUseCase
import org.koin.dsl.module

val usecase = module {
    single { TodayAstronomyPictureUseCase(get(), get(), get(), get()) }
    single { SavedAstronomyPicturesUseCase(get()) }
    single { SaveAstronomyPictureUseCase(get()) }
}