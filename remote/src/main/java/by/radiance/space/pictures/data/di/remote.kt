package by.radiance.space.pictures.data.di

import by.radiance.space.pictures.data.remote.repository.NasaRemoteAstronomyPictureRepository
import by.radiance.space.pictures.domain.repository.RemoteRepository
import org.koin.dsl.module

val remote = module {
    single { NasaRemoteAstronomyPictureRepository(get()) as RemoteRepository }
}