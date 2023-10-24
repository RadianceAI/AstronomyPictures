package by.radiance.space.pictures.data.di

import by.radiance.space.pictures.data.remote.client.AstronomyPictureClient
import by.radiance.space.pictures.data.remote.client.AstronomyPicturesAPI
import by.radiance.space.pictures.data.remote.mapper.PictureMapper
import by.radiance.space.pictures.data.remote.repository.NasaRemoteAstronomyPictureRepository
import by.radiance.space.pictures.domain.repository.RemoteAstronomyPictureRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val remote = module {
    single { PictureMapper() }
    single<Retrofit> { AstronomyPictureClient.client }

    single<AstronomyPicturesAPI> {
        get<Retrofit>().create(AstronomyPicturesAPI::class.java)
    }

    single<RemoteAstronomyPictureRepository> {
        NasaRemoteAstronomyPictureRepository(get(), get(), get())
    }
}