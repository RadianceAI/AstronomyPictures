package by.radiance.space.source.di

import by.radiance.space.pictures.domain.repository.AstronomyPictureRepository
import by.radiance.space.source.AstronomyPictureSource
import org.koin.dsl.module

val source = module {
    single { AstronomyPictureSource(get(), get()) as AstronomyPictureRepository }
}