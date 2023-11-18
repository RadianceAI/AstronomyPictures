package by.radiance.space.pictures.today.di

import by.radiance.space.pictures.today.repository.DataStoreAstronomyPictureRepository
import org.koin.dsl.module

val today = module {
    single { DataStoreAstronomyPictureRepository(get()) }
}