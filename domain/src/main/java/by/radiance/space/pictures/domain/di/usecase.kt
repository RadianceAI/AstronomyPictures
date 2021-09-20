package by.radiance.space.pictures.domain.di

import by.radiance.space.pictures.domain.usecase.GetLocalPictureUseCase
import by.radiance.space.pictures.domain.usecase.GetRandomPictureUseCase
import by.radiance.space.pictures.domain.usecase.GetTodayPictureUseCase
import org.koin.dsl.module


val usecase = module {
    single { GetTodayPictureUseCase(get(), get()) }
    single { GetRandomPictureUseCase(get(), get()) }
    single { GetLocalPictureUseCase(get()) }
}