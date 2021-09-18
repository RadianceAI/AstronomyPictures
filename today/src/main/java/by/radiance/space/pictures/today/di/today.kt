package by.radiance.space.pictures.data.di

import by.radiance.space.pictures.domain.repository.TempRepository
import by.radiance.space.pictures.today.repository.DataStoreAstronomyPictureRepository
import org.koin.dsl.module


val today = module {
    single { DataStoreAstronomyPictureRepository(get()) as TempRepository }
}