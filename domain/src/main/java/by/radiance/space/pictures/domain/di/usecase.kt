package by.radiance.space.pictures.domain.di

import by.radiance.space.pictures.domain.usecase.*
import org.koin.dsl.module


val usecase = module {
    single { GetAstronomyPicturesUseCase(get(), get(), get()) }
    single { GetRandomPictureUseCase(get(), get(), get()) }
    single { GetLocalPictureUseCase(get()) }
    single { SavePictureUseCase(get()) }
    single { DeletePictureUseCase(get()) }
    single { SetWallpaperUseCase(get()) }
    single { GetPictureUseCase(get(), get(), get()) }
    single { LikeUseCase(get(), get(), get()) }
    single { ShareUseCase(get()) }
}