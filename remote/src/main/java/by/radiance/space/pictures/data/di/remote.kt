package by.radiance.space.pictures.data.di

import by.radiance.space.pictures.data.remote.NasaRemoteAstronomyPictureRepository
import by.radiance.space.pictures.domain.repository.remote.RemoteAstronomyPictureRepository
import org.koin.dsl.module

val remote = module {
    single { NasaRemoteAstronomyPictureRepository() as RemoteAstronomyPictureRepository }
}