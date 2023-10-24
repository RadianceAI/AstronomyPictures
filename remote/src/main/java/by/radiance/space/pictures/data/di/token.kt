package by.radiance.space.pictures.data.di

import by.radiance.space.pictures.data.token.NasaTokenRepository
import org.koin.dsl.module

val token = module {
    single { NasaTokenRepository() }
}