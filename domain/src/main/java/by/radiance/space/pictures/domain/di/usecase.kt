package by.radiance.space.pictures.domain.di

import by.radiance.space.pictures.domain.usecase.*
import org.koin.dsl.module
import kotlin.math.sin


val usecase = module {
    single { GetTodayPictureUseCase(get(), get()) }
    single { GetRandomPictureUseCase(get(), get()) }
    single { GetLocalPictureUseCase(get()) }
    single { SavePictureUseCase(get()) }
    single { DeletePictureUseCase(get()) }
    single { SetToLockScreenUseCase(get()) }
    single { SetToBackgroundUseCase(get()) }
}