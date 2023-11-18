package by.radiance.space.pictures.domain.di

import by.radiance.space.pictures.domain.usecase.GetAstronomyPicturesUseCase
import by.radiance.space.pictures.domain.usecase.SetWallpaperUseCase
import org.koin.dsl.module

val usecase = module {
    single { GetAstronomyPicturesUseCase(get()) }
    single { SetWallpaperUseCase(get()) }
}