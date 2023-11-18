package by.radiance.space.pictures.local.di

import by.radiance.space.pictures.local.db.AppDatabase
import by.radiance.space.pictures.local.repository.RoomAstronomyPicturesRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val local = module {
    single { RoomAstronomyPicturesRepository(get()) }

    single { AppDatabase.getInstance(androidApplication()) }
    single { get<AppDatabase>().pictureDao() }
}
