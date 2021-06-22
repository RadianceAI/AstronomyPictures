package by.radiance.space.pictures.data.di

import by.radiance.space.pictures.data.token.NasaTokenRepository
import by.radiance.space.pictures.domain.repository.token.TokenRepository
import org.koin.dsl.module

val token= module {
    single { NasaTokenRepository() as TokenRepository }
}