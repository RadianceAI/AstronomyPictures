package by.radiance.space.pictures.data.di

import by.radiance.space.pictures.data.today.DataStoreTodayAstronomyPictureRepository
import by.radiance.space.pictures.domain.repository.today.TodayAstronomyPictureRepository
import org.koin.dsl.module

val today = module {
    single { DataStoreTodayAstronomyPictureRepository() as TodayAstronomyPictureRepository }
}