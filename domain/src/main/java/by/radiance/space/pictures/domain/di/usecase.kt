package by.radiance.space.pictures.domain.di

import by.radiance.space.pictures.domain.usecase.*
import org.koin.dsl.module


val usecase = module {
    single { GetAstronomyPicturesUseCase(get()) }
    single { GetLocalPictureUseCase(get()) }
    single { SavePictureUseCase(get()) }
    single { DeletePictureUseCase(get()) }
    single { SetWallpaperUseCase(get()) }
    single { LikeUseCase(get(), get(), get()) }
    single { ShareUseCase(get()) }
}